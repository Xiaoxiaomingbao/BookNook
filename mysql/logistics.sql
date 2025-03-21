CREATE DATABASE IF NOT EXISTS `book-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book-trade`;

CREATE TABLE IF NOT EXISTS `order_logistics` (
    `order_id` bigint NOT NULL COMMENT '订单id，与订单表一对一',
    `logistics_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '物流单号',
    `logistics_company` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '物流公司名称',
    `contact` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
    `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人手机号码',
    `province` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
    `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
    `town` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区',
    `street` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '街道',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

INSERT INTO `order_logistics` (`order_id`, `logistics_number`, `logistics_company`, `contact`, `mobile`, `province`, `city`, `town`, `street`) VALUES
(10001, 'JD1234567890', '京东物流', '张三', '13800138000', '北京市', '北京市', '海淀区', '中关村大街1号'),
(10002, 'SF1234567890', '顺丰速运', '李四', '13900139000', '上海市', '上海市', '浦东新区', '世纪大道2号'),
(10003, 'YT1234567890', '圆通速递', '王五', '13700137000', '广东省', '深圳市', '南山区', '科技园3号'),
(10004, 'ZTO12345678', '中通快递', '赵六', '13600136000', '浙江省', '杭州市', '西湖区', '文三路4号'),
(10005, 'STO12345678', '申通快递', '钱七', '13500135000', '四川省', '成都市', '武侯区', '人民南路5号'),
(10006, 'HT1234567890', '韵达快递', '孙八', '13400134000', '江苏省', '南京市', '玄武区', '中山路7号'),
(10007, 'TT1234567890', '天天快递', '周九', '13300133000', '山东省', '济南市', '历下区', '泉城路8号'),
(10008, 'YD1234567890', '邮政快递', '吴十', '13200132000', '陕西省', '西安市', '雁塔区', '长安南路9号');