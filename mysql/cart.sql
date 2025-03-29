CREATE DATABASE IF NOT EXISTS `book-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book-trade`;

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车条目id ',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `item_id` bigint NOT NULL COMMENT '商品id',
  `num` int NOT NULL DEFAULT '1' COMMENT '购买数量',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `price` int NOT NULL COMMENT '价格,单位：分',
  `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '商品图片',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key_user_item_id` (`user_id`,`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='订单详情表';

INSERT INTO `cart` (`user_id`, `item_id`, `num`, `name`, `price`, `image`) VALUES
(1, 1001, 2, 'Python编程从入门到实践', 9900, 'https://example.com/book1.jpg'),
(1, 1002, 1, 'effective java', 6900, 'https://example.com/book2.jpg'),
(2, 1003, 3, '数据结构与算法分析', 8900, 'https://example.com/book3.jpg'),
(2, 1004, 1, '操作系统导论', 7900, 'https://example.com/book4.jpg'),
(3, 1005, 2, '计算机网络', 6900, 'https://example.com/book5.jpg'),
(3, 1006, 1, '编译原理', 8900, 'https://example.com/book6.jpg'),
(4, 1007, 1, '数据库系统概论', 5900, 'https://example.com/book7.jpg'),
(4, 1008, 2, 'Java编程思想', 9900, 'https://example.com/book8.jpg'),
(5, 1009, 3, 'C++ Primer', 11900, 'https://example.com/book9.jpg'),
(5, 1010, 1, 'JavaScript高级程序设计', 9900, 'https://example.com/book10.jpg'),
(6, 1011, 2, 'HTML5与CSS3权威指南', 8900, 'https://example.com/book11.jpg'),
(6, 1012, 1, '深入理解计算机系统', 10900, 'https://example.com/book12.jpg'),
(7, 1013, 1, '算法导论', 12900, 'https://example.com/book13.jpg'),
(7, 1014, 2, '代码大全', 13900, 'https://example.com/book14.jpg'),
(8, 1015, 3, '设计模式之禅', 7900, 'https://example.com/book15.jpg'),
(8, 1016, 1, '重构：改善既有代码的设计', 9900, 'https://example.com/book16.jpg'),
(9, 1017, 2, 'clean code', 9900, 'https://example.com/book17.jpg'),
(9, 1018, 1, '计算机组成与设计', 10900, 'https://example.com/book18.jpg'),
(10, 1019, 1, '计算机体系结构', 9900, 'https://example.com/book19.jpg'),
(10, 1020, 2, '软件工程', 8900, 'https://example.com/book20.jpg');