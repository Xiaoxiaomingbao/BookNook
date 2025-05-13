CREATE DATABASE IF NOT EXISTS `book-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book-trade`;

CREATE TABLE IF NOT EXISTS `order` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `total_fee` int NOT NULL DEFAULT '0' COMMENT '总金额，单位为分',
    `payment_type` tinyint(1) unsigned zerofill NOT NULL COMMENT '支付类型，1、支付宝，2、微信，3、扣减余额',
    `user_id` bigint NOT NULL COMMENT '用户id',
    `status` tinyint(1) DEFAULT NULL COMMENT '订单的状态，1、未付款 2、已付款,未发货 3、已发货,未确认 4、确认收货，交易成功 5、交易取消，订单关闭 6、交易结束，已评价',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
    `consign_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
    `end_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
    `close_time` timestamp NULL DEFAULT NULL COMMENT '交易关闭时间',
    `comment_time` timestamp NULL DEFAULT NULL COMMENT '评价时间',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `multi_key_status_time` (`status`,`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

INSERT INTO `order` (`id`, `total_fee`, `payment_type`, `user_id`, `status`, `pay_time`, `consign_time`, `end_time`, `close_time`, `comment_time`) VALUES
(10001, 9900, 1, 1, 4, '2024-06-01 10:00:00', '2024-06-01 10:30:00', '2024-06-03 14:00:00', NULL, '2024-06-05 15:00:00'),
(10002, 19800, 2, 2, 4, '2024-06-02 11:30:00', '2024-06-02 12:00:00', '2024-06-04 15:00:00', NULL, '2024-06-06 16:00:00'),
(10003, 14850, 3, 3, 4, '2024-06-03 14:45:00', '2024-06-03 15:15:00', '2024-06-05 16:30:00', NULL, '2024-06-07 17:30:00'),
(10004, 7900, 1, 4, 2, '2024-06-04 09:15:00', NULL, NULL, NULL, NULL),
(10005, 12900, 2, 5, 3, '2024-06-05 16:20:00', '2024-06-05 17:00:00', NULL, NULL, NULL),
(10006, 6900, 3, 6, 1, NULL, NULL, NULL, NULL, NULL),
(10007, 25800, 1, 7, 5, NULL, NULL, NULL, '2024-06-07 12:30:00', NULL),
(10008, 19800, 2, 8, 6, '2024-06-08 15:45:00', '2024-06-08 16:15:00', '2024-06-10 17:30:00', NULL, '2024-06-12 18:30:00');