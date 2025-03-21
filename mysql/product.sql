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

INSERT INTO `product` (`uid`, `name`, `isbn`, `publisher`, `publish_time`, `author`, `category`, `description`, `condition`, `status`, `price`, `stock`) VALUES
(1, 'Python编程从入门到实践', '9787115424872', '人民邮电出版社', 2015, '埃里克·马瑟斯', '编程', '详细介绍了Python编程的基础知识和实际应用', 1, 1, 9900, 100),
(1, 'effective java', '9787115402899', '人民邮电出版社', 2007, '约书亚·布洛赫', '编程', 'Java编程的实践指南，介绍了有效的Java编程技巧', 2, 1, 6900, 50),
(2, '数据结构与算法分析', '9787302409126', '清华大学出版社', 2015, '克莱因伯格', '算法', '深入讲解了数据结构和算法分析的原理和应用', 1, 1, 8900, 30),
(2, '操作系统导论', '9787115402899', '人民邮电出版社', 2013, 'Remzi H. Arpaci-Dusseau', '操作系统', '全面介绍了操作系统的基本原理和设计思想', 2, 1, 7900, 40),
(3, '计算机网络', '9787115458848', '人民邮电出版社', 2017, '谢希仁', '网络', '系统讲解了计算机网络的基本概念和技术', 1, 1, 6900, 60),
(3, '编译原理', '9787115402899', '人民邮电出版社', 2011, 'Alfred V. Aho', '编译器', '深入介绍了编译器的设计原理和实现方法', 3, 1, 8900, 20),
(4, '数据库系统概论', '9787302409126', '清华大学出版社', 2013, '王珊', '数据库', '全面介绍了数据库系统的基本理论和实践', 1, 1, 5900, 70),
(4, 'Java编程思想', '9787115402899', '人民邮电出版社', 2007, '布鲁斯·埃克尔', '编程', '经典Java编程参考书籍，深入介绍了Java语言的特点和用法', 2, 1, 9900, 35),
(5, 'C++ Primer', '9787115402899', '人民邮电出版社', 2012, '斯坦利·B·利珀特', '编程', 'C++编程的入门和进阶指南', 1, 1, 11900, 25),
(5, 'JavaScript高级程序设计', '9787115402899', '人民邮电出版社', 2014, '尼古拉斯·泽卡斯', '前端', '深入介绍了JavaScript的高级编程技术和设计模式', 2, 1, 9900, 45),
(6, 'HTML5与CSS3权威指南', '9787115402899', '人民邮电出版社', 2016, '马特·弗莱纳', '前端', '详细讲解了HTML5和CSS3的新特性和应用', 1, 1, 8900, 55),
(6, '深入理解计算机系统', '9787115402899', '人民邮电出版社', 2016, '兰德尔·布莱恩特', '计算机', '从硬件和软件的角度深入理解计算机系统', 3, 1, 10900, 15),
(7, '算法导论', '9787115402899', '人民邮电出版社', 2013, '托马斯·科尔曼', '算法', '经典的算法教材，涵盖了算法设计和分析的各个方面', 1, 1, 12900, 20),
(7, '代码大全', '9787115402899', '人民邮电出版社', 2013, '史蒂夫·麦克康奈尔', '编程', '软件构造的实践指南，涵盖了代码编写的各个方面', 2, 1, 13900, 10),
(8, '设计模式之禅', '9787115402899', '人民邮电出版社', 2013, '波叔', '设计模式', '深入介绍了设计模式的概念和实际应用', 3, 1, 7900, 30),
(8, '重构：改善既有代码的设计', '9787115402899', '人民邮电出版社', 2013, '马丁·福勒', '编程', '介绍了如何在不改变软件外部行为的前提下改善代码结构', 2, 1, 9900, 25),
(9, 'clean code', '9787115402899', '人民邮电出版社', 2013, '罗伯特·C·马丁', '编程', '介绍了如何编写干净、可维护的代码', 1, 1, 9900, 40),
(9, '计算机组成与设计', '9787115402899', '人民邮电出版社', 2013, '帕特里克·J·赫林', '计算机', '全面介绍了计算机硬件的组成和设计原理', 2, 1, 10900, 20),
(10, '计算机体系结构', '9787115402899', '人民邮电出版社', 2013, '约翰·L·亨内斯', '计算机', '深入讲解了计算机体系结构的各个方面', 1, 1, 9900, 30),
(10, '软件工程', '9787115402899', '人民邮电出版社', 2013, '罗杰·S·普雷斯曼', '软件工程', '系统介绍了软件工程的基本原理和实践方法', 2, 1, 8900, 35);