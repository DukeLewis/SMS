package supermarket.manage.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 预警，websocket
 * @author：dukelewis
 * @date: 2024/4/15
 * @Copyright： https://github.com/DukeLewis
 */
@ServerEndpoint("/message/{uid}")
@Component
@Slf4j
public class WarnController {
    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的WebSocket对象。
     */
    private static ConcurrentHashMap<String, WarnController> webSocketMap = new ConcurrentHashMap<>();


    /**
     * 用来存在线连接用户信息
     */
    private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String uid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {


        try {
            this.session = session;
            this.uid = uid;
            //存在该用户id则直接覆盖
            if (webSocketMap.containsKey(uid)) {
                webSocketMap.remove(uid);
                webSocketMap.put(uid, this);
            } else {
                //加入用户的连接
                webSocketMap.put(uid, this);
                //在线人数加1
//            addOnlineCount();
            }
            sessionPool.put(uid, session);
            log.info("{}连接成功",this.uid);
        } catch (Exception e) {
            log.error("用户:" + uid + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(uid)) {
            //从map中删除
            webSocketMap.remove(uid);
            try {
                //关闭连接
                sessionPool.get(uid).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sessionPool.remove(uid);
        }
//        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 发生错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.uid + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 此为单点消息
     * @param message
     * @param userId
     */
    public void sendMessage(String message,String userId) {
        Session session = sessionPool.get(userId);
        if (session != null&&session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:"+message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为广播消息
     * @param message
     */
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        webSocketMap.forEach((k, v) -> {
            if (v.session.isOpen()) {
                try {
                    v.session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
