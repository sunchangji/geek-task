SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `customer_addr`
-- ----------------------------
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

SET FOREIGN_KEY_CHECKS = 1;
