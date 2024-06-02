create schema sms collate utf8_general_ci;


------------------------------


create table employee
(
    e_id        int(10) auto_increment comment '员工唯一标识'
        primary key,
    e_name      varchar(30)          null comment '员工姓名',
    e_sex       varchar(2)           null comment '员工性别',
    position    varchar(30)          null comment '岗位',
    e_phone     varchar(30)          null comment '联系方式',
    update_time datetime             null comment '更新时间',
    create_time datetime             null comment '创建时间',
    is_deleted  tinyint(5) default 0 null comment '逻辑删除字段'
);


--------------------

create table finance
(
    f_id        int(10) auto_increment comment '财务记录唯一标识'
        primary key,
    record_time datetime      null comment '记录日期',
    f_type      int(10)       null comment '类型，1为支出2为收入',
    amount      double(10, 2) null comment '数额',
    remark      varchar(50)   null comment '备注,水电费之类的',
    update_time datetime      null comment '更新时间',
    create_time datetime      null comment '创建时间',
    s_id        int(10)       null comment '销售字段',
    is_deleted  tinyint(5)    null comment '逻辑删除字段'
);


---------------
create table goods
(
    g_id                int(10) auto_increment comment '商品唯一标识'
        primary key,
    g_name              varchar(30)   null comment '商品名称',
    purchase_price      varchar(10)   null comment '进货价格',
    inventory           int           null comment '当前商品库存',
    inventory_threshold int           null,
    sell_price          varchar(10)   null comment '销售价格',
    g_brand             varchar(30)   null comment '商品品牌',
    g_category          varchar(30)   null comment '商品类别',
    g_type              varchar(30)   null comment '商品型号',
    g_specs             varchar(30)   null comment '商品规格',
    g_origin            varchar(30)   null comment '商品产地',
    supplier_id_list    varchar(5000) null comment '能供应该商品的供应商id列表',
    supplier_price_list varchar(5000) null comment '能提供该商品的供应商对应的价格列表',
    update_time         datetime      null comment '更新时间',
    create_time         datetime      null comment '创建时间',
    is_deleted          tinyint(5)    null comment '逻辑删除字段'
);

-------------------

create table restock
(
    r_id                int(20) auto_increment comment '进货单唯一标识'
        primary key,
    product_id_list     varchar(1000) null comment '商品id列表',
    product_number_list varchar(5000) null comment '商品数量列表',
    supplier_list       varchar(5000) null comment '对应商品列表中的每个商品是由哪个供应商进行供货，多个用逗号隔开',
    product_pricelist   varchar(5000) null comment '商品进价列表，对应上面的商品列表中的每个商品中的进价，多个用逗号隔开',
    arrive_time         datetime      null comment '到货时间',
    status              tinyint(6)    null comment '进货单状态位，0-已下单未到货，1-已到货，2-待定，3-延期',
    create_time         datetime      null comment '创建时间，同时也是下单时间',
    update_time         datetime      null comment '更新时间',
    is_deleted          tinyint(5)    null comment '逻辑删除字段'
);
----------------------

create table sales
(
    s_id        int(10) auto_increment comment '销售记录唯一标识'
        primary key,
    g_id      int(10)  null comment '商品id',

    sale_num    int(10)       null comment '销售数量',
    sale_time   datetime      null comment '销售时间',
    saler       varchar(50)       null comment '销售员',
    update_time datetime      null comment '更新时间',
    create_time datetime      null comment '创建时间',
    is_deleted  tinyint(5)    null comment '逻辑删除字段'

);


---------------------
create table supplier
(
    s_id        int(10) auto_increment comment '供应商唯一标识'
        primary key,
    s_name      varchar(300) null comment '供应商名称',
    s_principal varchar(20)  null comment '负责人姓名',
    s_phone     varchar(30)  null comment '联系电话',
    s_address   varchar(300) null comment '供应商地址',
    update_time datetime     null comment '更新时间',
    create_time datetime     null comment '创建时间',
    is_deleted  tinyint(5)   null comment '逻辑删除字段'
);


---------------------------------


create table user
(
    u_id         int(10) auto_increment comment '用户id'
        primary key,
    u_name       varchar(30)  null comment '用户名',
    password     varchar(100) null comment '密码',
    u_permission tinyint(5)   null comment '用户权限',
    update_time  datetime     null comment '更新时间',
    create_time  datetime     null comment '创建时间',
    is_deleted   tinyint(5)   null comment '逻辑删除字段'
);

---------------------------------


create table inventory
(
    id            int(10) auto_increment comment '唯一标识'
        primary key,
    g_id          int         null comment '商品id',
    g_name        varchar(30) null comment '商品名称',
    g_category    varchar(30) null comment '商品类别',
    inbound_num   int(10)     null comment '入库数量',
    inbound_time  datetime    null comment '入库时间',
    supplier      varchar(30) null comment '供应商',
    outbound_num  int(10)     null comment '出库数量',
    outbound_time datetime    null comment '出库时间',
    purpose       varchar(50) null comment '入库来源',
    update_time   datetime    null comment '更新时间',
    create_time   datetime    null comment '创建时间',
    is_deleted    tinyint(5)  null comment '逻辑删除字段'
);
