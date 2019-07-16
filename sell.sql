create table product_info(
    product_id varchar(32) not null ,
    product_name varchar(64) not null,
    product_price decimal(8, 2) not null,
    product_stock int not null,
    product_description varchar(64),
    product_icon varchar(512),
    product_status tinyint(3) default '0',
    category_type int not null,
    create_time timestamp not null default current_timestamp(),
    update_time timestamp not null default current_timestamp(),
    constraint product_info_pk
        primary key (product_id)
);
comment on table product_info is '商品表';
comment on column product_info.product_name is '商品名称';
comment on column product_info.product_price is '商品单价';
comment on column product_info.product_stock is '库存';
comment on column product_info.product_description is '商品描述';
comment on column product_info.product_status is '商品状态,0正常1下架';
comment on column product_info.product_icon is '小图';
comment on column product_info.category_type is '类目编号';
comment on column product_info.create_time is '创建时间';
comment on column product_info.update_time is '修改时间';

create table product_category (
    category_id int not null auto_increment,
    category_name varchar(64) not null,
    category_type int not null,
    create_time timestamp not null default current_timestamp(),
    update_time timestamp not null default current_timestamp(),
    constraint product_category_pk
        primary key (category_id)

);
create unique index product_category_categorytype_uindex
    on product_category (category_type);

comment on table product_category is '类目表';
comment on column product_category.category_name is '类目名字';
comment on column product_category.category_type is '类目编号';
comment on column product_category.create_time is '创建时间';
comment on column product_category.update_time is '修改时间';

create table order_master (
    order_id varchar(32) not null,
    buyer_name varchar(32) not null,
    buyer_phone varchar(32) not null,
    buyer_address varchar(128) not null,
    buyer_openid varchar(64) not null,
    order_amount decimal(8, 2) not null,
    order_status smallint (3) not null default '0',
    pay_status smallint (3) not null default '0',
    create_time timestamp not null default current_timestamp(),
    update_time timestamp not null default current_timestamp(),
    constraint order_master_pk
        primary key (order_id)

);
create index order_master__index
    on order_master (buyer_openid);

comment on table order_master is '订单表';
comment on column order_master.buyer_name is '买家姓名';
comment on column order_master.buyer_phone is '买家电话';
comment on column order_master.buyer_address is '买家地址';
comment on column order_master.buyer_openid is '买家微信openid';
comment on column order_master.order_amount is '订单总金额';
comment on column order_master.order_status is '订单状态，默认0为新下单';
comment on column order_master.pay_status is '支付状态，默认0未支付';
comment on column order_master.create_time is '创建时间';
comment on column order_master.update_time is '修改时间';


create table order_detail (
    detail_id varchar(32) not null,
    order_id varchar(32) not null,
    product_id varchar(32) not null,
    product_name varchar(64) not null,
    product_price decimal(8, 2) not null,
    product_quantity int not null,
    product_icon varchar(512) not null,
    create_time timestamp not null default current_timestamp(),
    update_time timestamp not null default current_timestamp(),
    constraint order_detail_pk
        primary key (detail_id),
);

create index order_detail__index
    on order_detail (order_id);

comment on table order_detail is '订单详情表';
comment on column order_detail.product_name is '商品名称';
comment on column order_detail.product_price is '商品价格';
comment on column order_detail.product_quantity is '商品数量';
comment on column order_detail.product_icon is '商品小图标';
comment on column order_detail.create_time is '创建时间';
comment on column order_detail.update_time is '修改时间';
