-- 新建库
CREATE DATABASE IF NOT EXISTS `book-product`;
USE `book-product`;

-- 新建表
CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片id',
    'pid' bigint NOT NULL COMMENT '商品id',
    `cover` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片URL',
    `number` int COMMENT '商品图片编号(从0开始)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品图片表';