-- 新建库
CREATE DATABASE IF NOT EXISTS `book-user`;
USE `book-user`;

-- 新建地址表
CREATE TABLE IF NOT EXISTS `address` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `uid` bigint NOT NULL COMMENT '用户ID',
    `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
    `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '市',
    `town` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '县/区',
    `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
    `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
    `is_default` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否是默认 1默认 0否',
    `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
    `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `user_id` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- 插入address表测试数据
INSERT INTO `address` (`uid`, `province`, `city`, `town`, `street`, `contact`,`mobile`, `is_default`,`notes`) VALUES
                                                                                                                   (1, '北京市', '北京市', '海淀区', '中关村大街1号', '张三', '13812345678', '1', '公司地址'),
                                                                                                                   (1, '上海市', '上海市', '浦东新区', '世纪大道2号', '张三', '13912345678', '0', '家庭地址'),
                                                                                                                   (2, '广东省', '深圳市', '南山区', '科技园3号', '李四', '13712345678', '1', '工作地址'),
                                                                                                                   (2, '浙江省', '杭州市', '西湖区', '文三路4号', '李四', '13612345678', '0', '老家地址'),
                                                                                                                   (3, '四川省', '成都市', '武侯区', '人民南路5号', '王五', '13512345678', '1', '常住地址'),
                                                                                                                   (3, '湖北省', '武汉市', '洪山区', '珞瑜路6号', '王五', '13412345678', '0', '临时地址'),
                                                                                                                   (4, '江苏省', '南京市', '玄武区', '中山路7号', '赵六', '13312345678', '1', '办公地址'),
                                                                                                                   (4, '山东省', '济南市', '历下区', '泉城路8号', '赵六', '13212345678', '0', '备用地址'),
                                                                                                                   (5, '陕西省', '西安市', '雁塔区', '长安南路9号', '钱七', '13112345678', '1', '主要地址'),
                                                                                                                   (5, '湖南省', '长沙市', '岳麓区', '岳麓大道10号', '钱七', '13012345678', '0', '其他地址'),
                                                                                                                   (6, '辽宁省', '沈阳市', '和平区', '青年大街11号', '孙八', '12912345678', '1', '常用地址'),
                                                                                                                   (6, '黑龙江省', '哈尔滨市', '南岗区', '黄河路12号', '孙八', '12812345678', '0', '次要地址'),
                                                                                                                   (7, '安徽省', '合肥市', '蜀山区', '黄山路13号', '周九', '12712345678', '1', '默认地址'),
                                                                                                                   (7, '福建省', '厦门市', '思明区', '湖滨南路14号', '周九', '12612345678', '0', '额外地址'),
                                                                                                                   (8, '天津市', '天津市', '河西区', '围堤道15号', '吴十', '12512345678', '1', '主要住址'),
                                                                                                                   (8, '河北省', '石家庄市', '长安区', '中山东路16号', '吴十', '12412345678', '0', '其他住址'),
                                                                                                                   (9, '河南省', '郑州市', '金水区', '金水路17号', '郑十一', '12312345678', '1', '常住住址'),
                                                                                                                   (9, '山西省', '太原市', '小店区', '长风街18号', '郑十一', '12212345678', '0', '临时住址'),
                                                                                                                   (10, '吉林省', '长春市', '南关区', '亚泰大街19号', '黄十二', '12112345678', '1', '默认住址'),
                                                                                                                   (10, '内蒙古自治区', '呼和浩特市', '新城区', '新华大街20号', '黄十二', '12012345678', '0', '备用住址');