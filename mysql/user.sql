-- 新建库
CREATE DATABASE IF NOT EXISTS `book-user`;
USE `book-user`;

-- 新建用户表
CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
    `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
    `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
    `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像URL',
    `status` int NULL DEFAULT 1 COMMENT '使用状态（1正常 2冻结）',
    `balance` int NULL DEFAULT NULL COMMENT '账户余额',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

INSERT INTO `user` (`username`, `password`, `phone`, `avatar`, `status`, `balance`, `create_time`, `update_time`) VALUES
('user1', 'password1', '13800138000', 'https://example.com/avatar1.jpg', 1, 1000, NOW(), NOW()),
('user2', 'password2', '13900139000', 'https://example.com/avatar2.jpg', 1, 2000, NOW(), NOW()),
('user3', 'password3', '13700137000', 'https://example.com/avatar3.jpg', 2, 1500, NOW(), NOW()),
('user4', 'password4', '13600136000', 'https://example.com/avatar4.jpg', 1, 3000, NOW(), NOW()),
('user5', 'password5', '13500135000', 'https://example.com/avatar5.jpg', 1, 500, NOW(), NOW()),
('user6', 'password6', '13400134000', 'https://example.com/avatar6.jpg', 2, 2500, NOW(), NOW()),
('user7', 'password7', '13300133000', 'https://example.com/avatar7.jpg', 1, 2000, NOW(), NOW()),
('user8', 'password8', '13200132000', 'https://example.com/avatar8.jpg', 1, 1800, NOW(), NOW()),
('user9', 'password9', '13100131000', 'https://example.com/avatar9.jpg', 2, 1200, NOW(), NOW()),
('user10', 'password10', '13000130000', 'https://example.com/avatar10.jpg', 1, 2200, NOW(), NOW());