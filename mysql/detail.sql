CREATE DATABASE IF NOT EXISTS `book-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book-trade`;

CREATE TABLE IF NOT EXISTS `detail` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
    `order_id` bigint NOT NULL COMMENT '订单id',
    `pid` bigint NOT NULL COMMENT 'sku商品id',
    `num` int NOT NULL COMMENT '购买数量',
    `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
    `price` int NOT NULL COMMENT '价格,单位：分',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `key_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='订单详情表';

INSERT INTO `detail` (`order_id`, `pid`, `num`, `name`, `price`) VALUES
(10001, 1001, 2, 'Python编程从入门到实践', 9900),
(10001, 1002, 1, 'effective java', 6900),
(10002, 1003, 3, '数据结构与算法分析', 8900),
(10002, 1004, 1, '操作系统导论', 7900),
(10003, 1005, 2, '计算机网络', 6900),
(10003, 1006, 1, '编译原理', 8900),
(10004, 1007, 1, '数据库系统概论', 5900),
(10004, 1008, 2, 'Java编程思想', 9900),
(10005, 1009, 3, 'C++ Primer', 11900),
(10005, 1010, 1, 'JavaScript高级程序设计', 9900),
(10006, 1011, 2, 'HTML5与CSS3权威指南', 8900),
(10006, 1012, 1, '深入理解计算机系统', 10900),
(10007, 1013, 1, '算法导论', 12900),
(10007, 1014, 2, '代码大全', 13900),
(10008, 1015, 3, '设计模式之禅', 7900),
(10008, 1016, 1, '重构：改善既有代码的设计', 9900);