-- 新建库
CREATE DATABASE IF NOT EXISTS `book-user`;
USE `book-user`;

-- 新建收藏表
CREATE TABLE IF NOT EXISTS `favorite` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    `uid` bigint NOT NULL COMMENT '用户id',
    `pid` bigint NOT NULL COMMENT '商品id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='收藏表';

INSERT INTO `favorite` (`uid`, `pid`, `create_time`) VALUES
(1, 1, '2024-05-01 10:00:00'),
(1, 2, '2024-05-02 11:30:00'),
(1, 3, '2024-05-03 14:45:00'),
(2, 4, '2024-05-04 09:15:00'),
(2, 5, '2024-05-05 16:20:00'),
(3, 6, '2024-05-06 18:00:00'),
(3, 7, '2024-05-07 12:30:00'),
(4, 8, '2024-05-08 15:45:00'),
(4, 9, '2024-05-09 17:10:00'),
(5, 10, '2024-05-10 19:00:00'),
(5, 11, '2024-05-11 13:25:00'),
(6, 12, '2024-05-12 11:00:00'),
(6, 13, '2024-05-13 14:30:00'),
(7, 14, '2024-05-14 16:45:00'),
(7, 15, '2024-05-15 09:15:00'),
(8, 16, '2024-05-16 12:30:00'),
(8, 17, '2024-05-17 18:00:00'),
(9, 18, '2024-05-18 15:45:00'),
(9, 19, '2024-05-19 17:10:00'),
(10, 20, '2024-05-20 19:00:00'),
(10, 1, '2024-05-21 13:25:00'),