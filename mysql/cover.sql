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

-- 插入product表（商品图片表）测试数据
INSERT INTO `product` (`pid`, `cover`, `number`) VALUES
(1, 'https://example.com/product_images/product1_0.jpg', 0),
(1, 'https://example.com/product_images/product1_1.jpg', 1),
(1, 'https://example.com/product_images/product1_2.jpg', 2),
(2, 'https://example.com/product_images/product2_0.jpg', 0),
(2, 'https://example.com/product_images/product2_1.jpg', 1),
(3, 'https://example.com/product_images/product3_0.jpg', 0),
(3, 'https://example.com/product_images/product3_1.jpg', 1),
(3, 'https://example.com/product_images/product3_2.jpg', 2),
(4, 'https://example.com/product_images/product4_0.jpg', 0),
(4, 'https://example.com/product_images/product4_1.jpg', 1),
(5, 'https://example.com/product_images/product5_0.jpg', 0),
(5, 'https://example.com/product_images/product5_1.jpg', 1),
(5, 'https://example.com/product_images/product5_2.jpg', 2),
(6, 'https://example.com/product_images/product6_0.jpg', 0),
(6, 'https://example.com/product_images/product6_1.jpg', 1),
(7, 'https://example.com/product_images/product7_0.jpg', 0),
(7, 'https://example.com/product_images/product7_1.jpg', 1),
(7, 'https://example.com/product_images/product7_2.jpg', 2),
(8, 'https://example.com/product_images/product8_0.jpg', 0),
(8, 'https://example.com/product_images/product8_1.jpg', 1),
(9, 'https://example.com/product_images/product9_0.jpg', 0),
(9, 'https://example.com/product_images/product9_1.jpg', 1),
(9, 'https://example.com/product_images/product9_2.jpg', 2),
(10, 'https://example.com/product_images/product10_0.jpg', 0),
(10, 'https://example.com/product_images/product10_1.jpg', 1),
(11, 'https://example.com/product_images/product11_0.jpg', 0),
(11, 'https://example.com/product_images/product11_2.jpg', 1),
(12, 'https://example.com/product_images/product12_0.jpg', 0),
(12, 'https://example.com/product_images/product12_1.jpg', 1),
(13, 'https://example.com/product_images/product13_0.jpg', 0),
(13, 'https://example.com/product_images/product13_1.jpg', 1),
(13, 'https://example.com/product_images/product13_2.jpg', 2),
(14, 'https://example.com/product_images/product14_0.jpg', 0),
(14, 'https://example.com/product_images/product14_1.jpg', 1),
(15, 'https://example.com/product_images/product15_0.jpg', 0),
(15, 'https://example.com/product_images/product15_1.jpg', 1),
(15, 'https://example.com/product_images/product15_2.jpg', 2),
(16, 'https://example.com/product_images/product16_0.jpg', 0),
(16, 'https://example.com/product_images/product16_1.jpg', 1),
(17, 'https://example.com/product_images/product17_0.jpg', 0),
(17, 'https://example.com/product_images/product17_1.jpg', 1),
(17, 'https://example.com/product_images/product17_2.jpg', 2),
(18, 'https://example.com/product_images/product18_0.jpg', 0),
(18, 'https://example.com/product_images/product18_1.jpg', 1),
(19, 'https://example.com/product_images/product19_0.jpg', 0),
(19, 'https://example.com/product_images/product19_1.jpg', 1),
(19, 'https://example.com/product_images/product19_2.jpg', 2),
(20, 'https://example.com/product_images/product20_0.jpg', 0),
(20, 'https://example.com/product_images/product20_1.jpg', 1);