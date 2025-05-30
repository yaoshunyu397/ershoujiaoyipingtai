-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人',
  `useraddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '地址信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'admin', '0.png', 'ADMIN', NULL, NULL);

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家介绍',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核状态',
  `balance` decimal(10,2) NULL DEFAULT 0.00 COMMENT '账户金额',
  `level` ENUM('5', '4', '3', '2', '1') NOT NULL DEFAULT '5' COMMENT '商家等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商家信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (5, 'ling', '123456', 'ling', 'u=2895902758,4240700774&fm=253&fmt=auto&app=120&f=JPEG.webp', 'BUSINESS', NULL, NULL, '不错的商家', '审核通过', 1000.00, '5');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `business_id` int NULL DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收藏信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `business_id` int NULL DEFAULT NULL COMMENT '店铺ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (5, '1', '欢迎访问二手交易平台！', '2025-5-19', 'admin');
INSERT INTO `notice` VALUES (6, '2', '222！', '2025-5-20', 'admin');
INSERT INTO `notice` VALUES (7, '2', '333！', '2025-5-21', 'admin');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `business_id` int NULL DEFAULT NULL COMMENT '店铺ID',
  `num` int NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品主图',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品介绍',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计件单位',
  `count` int NULL DEFAULT 0 COMMENT '商品销量',
  `type_id` int NULL DEFAULT NULL COMMENT '分类ID',
  `business_id` int NULL DEFAULT NULL COMMENT '商家ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (23, '冲锋衣', 'http://localhost:9090/files/1747732295736-u=2251572628,3509920198&fm=253&fmt=auto&app=138&f=JPEG.webp', '<p>不要了</p>', 50.00, '件', 0, 19, 5);
INSERT INTO `goods` VALUES (25, '长袖毛衣', 'http://localhost:9090/files/1747732365484-u=3157886781,43715778&fm=253&fmt=auto&app=138&f=JPEG.webp', NULL, 40.00, '件', 0, 19, 5);
INSERT INTO `goods` VALUES (16, '钳子', 'http://localhost:9090/files/1724995081903-86b3b3e58f5d42be92076cff0837b829.jpg', NULL, 9.90, '件', 3, 20, 5);
INSERT INTO `goods` VALUES (18, '没开封的椰子', 'http://localhost:9090/files/1724995260831-9f4d907e7f7e48408b134198a9968c2c.png', '<p>买的一箱吃不完了</p>', 5.00, '个', 5, 21, 5);
INSERT INTO `goods` VALUES (21, '白色衬衫', 'http://localhost:9090/files/1724995425405-f8ae37e71b654257a8069f766307aa92.png', '<p>女装</p>', 60.00, '件', 1, 19, 5);
INSERT INTO `goods` VALUES (22, '手布袋', 'http://localhost:9090/files/1747730921728-u=3095263295,1329891429&fm=253&fmt=auto&app=138&f=JPEG.webp', '<p>不要了，99新</p>', 20.00, '袋', 0, 19, 6);
INSERT INTO `goods` VALUES (24, '外套加绒衣', 'http://localhost:9090/files/1747732328042-src=http___img.alicdn.com_i1_3243857521_O1CN01DsoRf425QfbVO4uxw_!!3243857521.jpg&refer=http___img.alicdn.webp', NULL, 120.00, '件', 0, 19, 5);
INSERT INTO `goods` VALUES (26, 't袖', 'http://localhost:9090/files/1747732386044-u=3731967229,2963784154&fm=253&fmt=auto&app=120&f=JPEG.webp', NULL, 40.00, '件', 0, 19, 5);
INSERT INTO `goods` VALUES (15, '面包片', 'http://localhost:9090/files/1724995043180-43bbba44ddf44b01a773195de01f403b.jpg', NULL, 5.00, '个', 1, 21, 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `business_id` int NULL DEFAULT NULL COMMENT '商家ID',
  `num` int NULL DEFAULT NULL COMMENT '商品数量',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `address_id` int NULL DEFAULT NULL COMMENT '地址ID',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态(待收货/已完成/已评价/退货中/退货成功/退货失败)',
  `transaction_time` datetime NULL DEFAULT NULL COMMENT '交易完成时间',
  `reject_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '退货驳回理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单信息表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类描述',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (17, '数码', NULL, 'http://localhost:9090/files/1724994884535-数码家电类目.png');
INSERT INTO `type` VALUES (18, '书籍', NULL, 'http://localhost:9090/files/1724994878972-图书.png');
INSERT INTO `type` VALUES (19, '衣服', NULL, 'http://localhost:9090/files/1724994874510-T恤.png');
INSERT INTO `type` VALUES (20, '运动', NULL, 'http://localhost:9090/files/1724994869150-运动鞋.png');
INSERT INTO `type` VALUES (21, '食品', NULL, 'http://localhost:9090/files/1724994834258-食品类目.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `bank_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `balance` decimal(10,2) NULL DEFAULT 0.00 COMMENT '账户余额',
  `points` int NULL DEFAULT 0 COMMENT '用户积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (7, 'yang', '123456', 'yang', 'u=2738011829,2244692575&fm=253&fmt=auto&app=120&f=JPEG.webp', 'USER', NULL, NULL, 12400000, 2000.00, 0.0);

SET FOREIGN_KEY_CHECKS = 1;
