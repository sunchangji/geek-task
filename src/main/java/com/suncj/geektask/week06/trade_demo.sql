SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `brand_info`
-- ----------------------------
DROP TABLE IF EXISTS `brand_info`;
CREATE TABLE `brand_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '品牌ID',
  `brand_name` varchar(50) NOT NULL COMMENT '品牌名称',
  `telephone` varchar(50) NOT NULL COMMENT '联系电话',
  `brand_logo` varchar(100) DEFAULT NULL COMMENT '品牌logo URL',
  `brand_desc` varchar(150) DEFAULT NULL COMMENT '品牌描述',
  `brand_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '品牌状态,0禁用,1启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌信息表';

DROP TABLE IF EXISTS `customer_addr`;
CREATE TABLE `customer_addr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `customer_id` int(10) unsigned NOT NULL COMMENT 'customer_login表的自增ID',
  `zip` smallint(6) NOT NULL COMMENT '邮编',
  `province` smallint(6) NOT NULL COMMENT '地区表中省份的ID',
  `city` smallint(6) NOT NULL COMMENT '地区表中城市的ID',
  `district` smallint(6) NOT NULL COMMENT '地区表中的区ID',
  `address` varchar(200) NOT NULL COMMENT '具体的地址门牌号',
  `defaulted` tinyint(4) NOT NULL COMMENT '是否默认',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户地址表';

DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `customer_id` bigint(20) unsigned NOT NULL COMMENT 'customer_login表的自增ID',
  `customer_name` varchar(20) NOT NULL COMMENT '用户真实姓名',
  `identity_card_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '证件类型：1 身份证，2 军官证，3 护照',
  `identity_card_no` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `mobile_phone` int(10) unsigned DEFAULT NULL COMMENT '手机号',
  `customer_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `user_point` int(11) NOT NULL DEFAULT '0' COMMENT '用户积分',
  `birthday` datetime DEFAULT NULL COMMENT '会员生日',
  `user_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '用户余额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

DROP TABLE IF EXISTS `customer_login`;
CREATE TABLE `customer_login` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(20) NOT NULL COMMENT '用户登录名',
  `password` char(32) NOT NULL COMMENT 'md5加密的密码',
  `user_stats` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';

DROP TABLE IF EXISTS `order_cart`;
CREATE TABLE `order_cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `customer_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `product_amount` int(11) NOT NULL COMMENT '加入购物车商品数量',
  `price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入购物车时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(10) NOT NULL COMMENT '分类名称',
  `category_code` varchar(10) NOT NULL COMMENT '分类编码',
  `parent_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '父分类ID',
  `category_level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '分类层级',
  `category_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '分类状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `product_no` char(16) NOT NULL COMMENT '商品条码',
  `bar_code` varchar(50) NOT NULL COMMENT '商品编码',
  `brand_id` int(10) unsigned NOT NULL COMMENT '品牌表的ID',
  `one_category_id` smallint(5) unsigned NOT NULL COMMENT '一级分类ID',
  `two_category_id` smallint(5) unsigned NOT NULL COMMENT '二级分类ID',
  `three_category_id` smallint(5) unsigned NOT NULL COMMENT '三级分类ID',
  `supplier_id` int(10) unsigned NOT NULL COMMENT '商品的供应商ID',
  `price` decimal(8,2) NOT NULL COMMENT '商品销售价格',
  `average_cost` decimal(18,2) NOT NULL COMMENT '商品平均成本价',
  `publish_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '上下架状态：0下架1上架',
  `audit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态：0未审核，1已审核',
  `spec` varchar(512) NOT NULL DEFAULT '' COMMENT '规格',
  `photo_url` varchar(512) NOT NULL DEFAULT '' COMMENT '商品图片',
  `production_date` datetime NOT NULL COMMENT '生产日期',
  `shelf_life` int(11) NOT NULL COMMENT '商品有效期',
  `descript` text NOT NULL COMMENT '商品描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

DROP TABLE IF EXISTS `shipping_info`;
CREATE TABLE `shipping_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `ship_name` varchar(20) NOT NULL COMMENT '物流公司名称',
  `ship_contact` varchar(20) NOT NULL COMMENT '物流公司联系人',
  `telephone` varchar(20) NOT NULL COMMENT '物流公司联系电话',
  `price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '配送价格',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物流公司信息表';

DROP TABLE IF EXISTS `supplier_info`;
CREATE TABLE `supplier_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplier_code` char(8) NOT NULL COMMENT '供应商编码',
  `supplier_name` char(50) NOT NULL COMMENT '供应商名称',
  `supplier_type` tinyint(4) NOT NULL COMMENT '供应商类型：1.自营，2.平台',
  `link_man` varchar(10) NOT NULL COMMENT '供应商联系人',
  `phone_number` varchar(50) NOT NULL COMMENT '联系电话',
  `bank_name` varchar(50) NOT NULL COMMENT '供应商开户银行名称',
  `bank_account` varchar(50) NOT NULL COMMENT '银行账号',
  `address` varchar(200) NOT NULL COMMENT '供应商地址',
  `supplier_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0禁止，1启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商信息表';


DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(128) unsigned NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn',
  `customer_id` bigint(20) unsigned NOT NULL COMMENT '下单人ID',
  `shipping_user` varchar(10) NOT NULL COMMENT '收货人姓名',
  `province` smallint(6) NOT NULL COMMENT '省',
  `city` smallint(6) NOT NULL COMMENT '市',
  `district` smallint(6) NOT NULL COMMENT '区',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `payment_method` tinyint(4) NOT NULL COMMENT '支付方式：1现金，2余额，3网银，4支付宝，5微信',
  `order_money` decimal(8,2) NOT NULL COMMENT '订单金额',
  `district_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `shipping_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `payment_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `shipping_comp_name` varchar(10) DEFAULT NULL COMMENT '快递公司名称',
  `shipping_sn` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

DROP TABLE IF EXISTS `trade_order_detail`;
CREATE TABLE `trade_order_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单表ID',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '订单商品ID',
  `product_name` varchar(50) NOT NULL COMMENT '商品名称',
  `buy_num` int(11) NOT NULL DEFAULT '1' COMMENT '购买商品数量',
  `product_price` decimal(8,2) NOT NULL COMMENT '购买商品单价',
  `average_cost` decimal(8,2) NOT NULL COMMENT '平均成本价格',
  `fee_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠分摊金额',
  `warehouse_id` bigint(20) unsigned NOT NULL COMMENT '仓库ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

DROP TABLE IF EXISTS `warehouse_info`;
CREATE TABLE `warehouse_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `warehouse_code` char(5) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(10) NOT NULL COMMENT '仓库名称',
  `warehouse_phone` varchar(20) NOT NULL COMMENT '仓库电话',
  `contact` varchar(10) NOT NULL COMMENT '仓库联系人',
  `province` smallint(6) NOT NULL COMMENT '省',
  `city` smallint(6) NOT NULL COMMENT '市',
  `distrct` smallint(6) NOT NULL COMMENT '区',
  `address` varchar(100) NOT NULL COMMENT '仓库地址',
  `warehouse_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '仓库状态：0禁用，1启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库信息表';