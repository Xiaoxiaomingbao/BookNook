CREATE DATABASE IF NOT EXISTS `book-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book-trade`;

CREATE TABLE IF NOT EXISTS `detail` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
    `order_id` bigint NOT NULL COMMENT '订单id',
    `item_id` bigint NOT NULL COMMENT 'sku商品id',
    `num` int NOT NULL COMMENT '购买数量',
    `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
    `price` int NOT NULL COMMENT '价格,单位：分',
    `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '商品图片',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `key_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='订单详情表';

INSERT INTO `detail` (`order_id`, `item_id`, `num`, `name`, `price`, `image`) VALUES
(10001, 1001, 2, 'Python编程从入门到实践', 9900, 'https://example.com/book1.jpg'),
(10001, 1002, 1, 'effective java', 6900, 'https://example.com/book2.jpg'),
(10002, 1003, 3, '数据结构与算法分析', 8900, 'https://example.com/book3.jpg'),
(10002, 1004, 1, '操作系统导论', 7900, 'https://example.com/book4.jpg'),
(10003, 1005, 2, '计算机网络', 6900, 'https://example.com/book5.jpg'),
(10003, 1006, 1, '编译原理', 8900, 'https://example.com/book6.jpg'),
(10004, 1007, 1, '数据库系统概论', 5900, 'https://example.com/book7.jpg'),
(10004, 1008, 2, 'Java编程思想', 9900, 'https://example.com/book8.jpg'),
(10005, 1009, 3, 'C++ Primer', 11900, 'https://example.com/book9.jpg'),
(10005, 1010, 1, 'JavaScript高级程序设计', 9900, 'https://example.com/book10.jpg'),
(10006, 1011, 2, 'HTML5与CSS3权威指南', 8900, 'https://example.com/book11.jpg'),
(10006, 1012, 1, '深入理解计算机系统', 10900, 'https://example.com/book12.jpg'),
(10007, 1013, 1, '算法导论', 12900, 'https://example.com/book13.jpg'),
(10007, 1014, 2, '代码大全', 13900, 'https://example.com/book14.jpg'),
(10008, 1015, 3, '设计模式之禅', 7900, 'https://example.com/book15.jpg'),
(10008, 1016, 1, '重构：改善既有代码的设计', 9900, 'https://example.com/book16.jpg');