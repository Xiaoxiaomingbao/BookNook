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