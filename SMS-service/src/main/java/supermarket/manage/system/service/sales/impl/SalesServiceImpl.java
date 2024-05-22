package supermarket.manage.system.service.sales.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supermarket.manage.system.common.annotation.InfoLog;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.InfoType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.*;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SalesInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.EmployeeMapper;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.SalesMapper;
import supermarket.manage.system.service.finance.FinanceService;
import supermarket.manage.system.service.sales.SalesService;
import supermarket.manage.system.service.support.CommonalitySupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Service实现
* @createDate 2024-05-07 00:19:13
*/
@Service
@Slf4j
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales>
    implements SalesService {

    @Resource
    private SalesMapper salesMapper;

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private FinanceMapper financeMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @InfoLog(infoType = InfoType.ADD, item = "销售", infoItemIdExpression = "#sales.sId")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean informationEntry(Sales sales) {
        //插入记录，插入的是表，

//        Sales sales = salesMapper.selectById(SalesInfoDTO.getSid());
        Goods goods = goodsMapper.selectById(sales.getGId());
        if(goods==null||goods.getInventory()<sales.getSaleNum()){
            return false;
        }
        Employee employee = employeeMapper.selectByName(sales.getSaler());

        if(employee==null){
            return false;
        }
        //商品销售金额
        Double money1 =  Double.parseDouble(goods.getSellPrice()) * sales.getSaleNum();
        //商品支出金额
        Double money2 = Double.parseDouble(goods.getPurchasePrice()) * sales.getSaleNum();

        return save(Sales.builder()
                .sId(sales.getSId())
                .gId(sales.getGId())
                .saleNum(sales.getSaleNum())
                .saleTime(new Date())
                .saler(sales.getSaler())
                .updateTime(new Date())
                .createTime(new Date())
                .isDeleted(0)
                .build())

                && goodsMapper.updateById(Goods.builder()
                .gId(goods.getGId())
                .inventory(goods.getInventory()-sales.getSaleNum())
                .build()
        )>0
                //计入收入
                && financeMapper.insert(Finance.builder()
                .recordTime(new Date())
                .fType(2)
                .amount(money1)
                .remark("商品"+goods.getGName()+"的销售金额")
                .createTime(new Date())
                .updateTime(new Date())
                .sId(sales.getSId())
                .isDeleted(0)

                .build())>0
                //计入支出
                && financeMapper.insert(Finance.builder()
                .recordTime(new Date())
                .fType(1)
                .amount(money2)
                .remark("商品"+goods.getGName()+"的进货金额")
                .createTime(new Date())
                .updateTime(new Date())
                .sId(sales.getSId())
                .isDeleted(0)
                .build())>0;




    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean informationModification(SalesInfoDTO salesInfoDTO) {

        //sales是数据库表里面的，salesinfodto是输入的修改对象
        Sales sales = salesMapper.selectById(salesInfoDTO.getSid());
        Integer gid = goodsMapper.selectByName(salesInfoDTO.getGname());
//        if(sales.getSaleNum()!=salesInfoDTO.getSaleNum()){
//            return false;
//        }
        //Integer resalenum = salesInfoDTO.getSaleNum();
        Goods goods = goodsMapper.selectById(gid);
        Integer ven = goods.getInventory()+sales.getSaleNum();
        if(ven<salesInfoDTO.getSaleNum()){
            return false;
        }
        System.out.println("1111111111");
        List<Finance> listallbysid =  FinanceMapper.selectallbysid(sales.getSId());
        System.out.println("222222222");
        //商品收入金额
        Double money1 =  Double.parseDouble(goods.getSellPrice()) * salesInfoDTO.getSaleNum();
        //商品支出金额
        Double money2 = Double.parseDouble(goods.getPurchasePrice()) * salesInfoDTO.getSaleNum();
        if(listallbysid==null){
            System.out.println("fafsfsfs");
            return false;
        }
        for (Finance f: listallbysid) {
            if(f.getFType()==1){
                //支出
                int i = financeMapper.updateById(Finance.builder()
                        .fId(f.getFId())
                        .amount(money1)
                        .updateTime(new Date())
                        .remark("商品"+goods.getGName()+"的进货金额")
                        .build());
            }else {
                //收入
                int i = financeMapper.updateById(Finance.builder()
                        .fId(f.getFId())
                        .amount(money2)
                        .updateTime(new Date())
                        .remark("商品"+goods.getGName()+"的销售金额")
                        .build());
            }
        }

        return updateById(sales.builder()
                .sId(salesInfoDTO.getSid())
                .gId(goods.getGId())
                .saleNum(salesInfoDTO.getSaleNum())
                .saler(salesInfoDTO.getSaler())
                .updateTime(new Date())
                .build())
                && goodsMapper.updateById(Goods.builder()
                .gId(gid)
                .inventory(ven-salesInfoDTO.getSaleNum())
                .updateTime(new Date())
                .build())>0;

        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean informationDeletion(SalesInfoDTO salesInfoDTO) {
        Integer gid = goodsMapper.selectByName(salesInfoDTO.getGname());
        Goods goods = goodsMapper.selectById(gid);
        if(gid==null){
            return false;
        }

        return updateById(Sales.builder()
                .sId(salesInfoDTO.getSid())
                .updateTime(new Date())
                .isDeleted(1)
                .build())
                && goodsMapper.updateById(Goods.builder()
                .gId(gid)
                .inventory(goods.getInventory()+salesInfoDTO.getSaleNum())
                        .updateTime(new Date())
                .build()
        )>0
                //删除收入和支出
                && financeMapper.updateBysId(salesInfoDTO.getSid()
        )>0;

    }

    @Override
    public PageResult informationQueryALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        Integer isdeleted =0;
        QueryWrapper<SalesInfoDTO> queryWrapper = new QueryWrapper<SalesInfoDTO>();

        List<SalesInfoDTO> listall = salesMapper.listall(isdeleted);
        return new PageResult(
                pag, pagesize, Long.valueOf(listall.size()), listall
        );
    }

    @Override
    public PageResult informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        if (null == pageQueryDTO.getKeyword()||null==pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }


        if(pageQueryDTO.getKeywordType().equals("time")){
            //获取查询类型
            String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.SALES);
            //log.info("查询类型：{}",queryTypeName);
            System.out.println("查询类型：{}"+queryTypeName);
            if(null==queryTypeName){
                throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
            }
            //0为未删除，1为已删除
            QueryWrapper<Sales> queryWrapper = new QueryWrapper<Sales>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
            //判断查询类型执行对应查询
            //日期查询

            List<Sales> byTime = salesMapper.getByTime(pageQueryDTO.getKeyword());

            log.info("结果:{}",byTime);
            return new PageResult(
                    pag, pagesize, Long.valueOf(byTime.size()), byTime
            );
        }else if (pageQueryDTO.getKeywordType().equals("name")){
            //获取查询类型
            //商品名称
            String gname = pageQueryDTO.getKeyword();
            QueryWrapper<SalesInfoDTO> queryWrapper2 = new QueryWrapper<SalesInfoDTO>();
            List<SalesInfoDTO> listbygname = salesMapper.listbygname(gname);
            return new PageResult(
                    pag, pagesize, Long.valueOf(listbygname.size()), listbygname
            );


        }else{
            //获取查询类型
            //销售员
            String saler = pageQueryDTO.getKeyword();
            QueryWrapper<SalesInfoDTO> queryWrapper2 = new QueryWrapper<SalesInfoDTO>();
            List<SalesInfoDTO> listbysaler = salesMapper.listbysaler(saler);
            return new PageResult(
                    pag, pagesize, Long.valueOf(listbysaler.size()), listbysaler
            );
        }


    }
}




