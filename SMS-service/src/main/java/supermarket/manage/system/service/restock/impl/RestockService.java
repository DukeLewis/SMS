package supermarket.manage.system.service.restock.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.model.domain.Restock;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.RestockMapper;
import supermarket.manage.system.service.restock.IRestockService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author ASUS
* @description 针对表【restock】的数据库操作Service实现
* @createDate 2024-04-21 14:58:04
*/
@Service
public class RestockService extends ServiceImpl<RestockMapper, Restock>
   implements IRestockService {

    @Resource
    RestockMapper restockMapper;


    @Override
    public boolean addRestock(RestockInfoDTO restockInfoDTO) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        getAllList(restockInfoDTO,sb,sb1,sb2);
        return save(Restock.builder()
                .productList(sb.toString())
                .supplierList(sb2.toString())
                .productPricelist(sb1.toString())
                .arriveTime(restockInfoDTO.getArriveTime())
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0).build());
    }

    @Override
    public boolean updateRestock(RestockInfoDTO restockInfoDTO) {
        //跟上面方法部分重复，可以考虑做对象封装，但是懒得做
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        getAllList(restockInfoDTO,sb,sb1,sb2);
        //todo 关于更新操作，其实需要满足isDeleted操作为0（即没有被逻辑删除），但是这里没有校验，其他几个的更新方法也是，之后再改
        return updateById(
                Restock.builder()
                        .rId(restockInfoDTO.getRId())
                        .productList(sb.toString())
                        .supplierList(sb2.toString())
                        .productPricelist(sb1.toString())
                        .arriveTime(restockInfoDTO.getArriveTime())
                        //注意，如果这里进行了更改那么就是删除该记录
                        .isDeleted(restockInfoDTO.getIsDeleted())
                        .updateTime(new Date())
                        .build()
        );
    }

    @Override
    public PageResult queryRestockall(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, 1)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    @Override
    public PageResult queryRestock(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>().eq(Constant.RESTOCK_ID, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, 1)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    private void getAllList(RestockInfoDTO restockInfoDTO,StringBuilder sb,StringBuilder sb1,StringBuilder sb2){
        List<String> productIdList = restockInfoDTO.getProductIdList();
        List<String> productNumberList = restockInfoDTO.getProductNumberList();
        List<String> productPricelist = restockInfoDTO.getProductPricelist();
        List<String> supplierList = restockInfoDTO.getSupplierList();
        int n=productIdList.size();
        for (int i = 0; i < n; i++) {
            StringBuilder sub = new StringBuilder(productIdList.get(i) + productNumberList.get(i));
            if(i!=n-1){
                sub.append(",");
            }
            sb.append(sub);
        }
        //辅助gc
        productIdList=null;
        productNumberList=null;
        for (int i = 0; i < n; i++) {
            StringBuilder sub = new StringBuilder(productPricelist.get(i));
            StringBuilder sub1 = new StringBuilder(supplierList.get(i));
            if(i!=n-1){
                sub.append(",");
                sub1.append(",");
            }
            sb1.append(sub);
            sb2.append(sub1);
        }
        productPricelist=null;
        supplierList=null;
    }
}




