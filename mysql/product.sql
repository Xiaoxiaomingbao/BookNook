-- 新建库
CREATE DATABASE IF NOT EXISTS `book-product`;
USE `book-product`;

-- 新建表
CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
    'uid' bigint NOT NULL COMMENT '卖家的用户id',
    `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
    'isbn' varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ISBN(不带横杠)',
    'publisher' varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出版社',
    'publish_time' year DEFAULT NULL COMMENT '出版时间',
    'author' varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作者',
    `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类目名称',
    `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品描述',
    'condition' int DEFAULT '1' COMMENT '商品成色 1-全新，2-九成新，3-七成新，4-五成新',
    `status` int DEFAULT '2' COMMENT '商品状态 1-正常，2-下架，3-删除',
    `price` int NOT NULL DEFAULT '0' COMMENT '价格（分）',
    `stock` int UNSIGNED NOT NULL COMMENT '库存数量',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `status` (`status`) USING BTREE,
    KEY `updated` (`update_time`) USING BTREE,
    KEY `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品表';
