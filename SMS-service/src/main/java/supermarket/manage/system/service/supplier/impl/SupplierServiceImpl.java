package supermarket.manage.system.service.supplier.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.domain.Supplier;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SupplierInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.SupplierMapper;
import supermarket.manage.system.service.supplier.SupplierService;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author ASUS
* @description 针对表【supplier】的数据库操作Service实现
* @createDate 2024-05-07 00:19:18
*/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
    implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public boolean informationEntry(SupplierInfoDTO supplierInfoDTO) {
        return save(Supplier.builder()
                .sName(supplierInfoDTO.getSname())
                .sPrincipal(supplierInfoDTO.getSprincipal())
                .sPhone(supplierInfoDTO.getSphone())
                .sAddress(supplierInfoDTO.getSaddress())
                .updateTime(new Date())
                .createTime(new Date())
                .isDeleted(0)
                .build());
    }

    @Override
    public boolean informationModification(SupplierInfoDTO supplierInfoDTO) {
        return updateById(Supplier.builder()
                .sId(supplierInfoDTO.getSid())
                .sName(supplierInfoDTO.getSname())
                .sPrincipal(supplierInfoDTO.getSprincipal())
                .sPhone(supplierInfoDTO.getSphone())
                .sAddress(supplierInfoDTO.getSaddress())
                .updateTime(new Date())
                .build());
    }



    @Override
    public PageResult informationQueryByName(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        Page<Supplier> page = supplierMapper.selectPage(
                new Page<Supplier>(pag,pagesize),
                new QueryWrapper<Supplier>().eq(Constant.SUPPLIER_NAME, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED,1)
        );
        return null;
    }
    @Override
    public PageResult informationQueryById(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        Page<Supplier> page = supplierMapper.selectPage(
                new Page<Supplier>(pag,pagesize),
                new QueryWrapper<Supplier>().eq(Constant.SUPPLIER_ID, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED,1)
        );
        return null;
    }
}




