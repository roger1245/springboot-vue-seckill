select * from product;
INSERT INTO `product` VALUES (NULL, 'Redmi K50', 1, '全线拉满的冷血旗舰', '「新品热卖中！至高享24期免息」骁龙8 年度旗舰｜「双VC」强劲散热｜以电竞
标准打造｜120W神仙秒充｜OLED 柔性直屏｜JBL 宽频四单元扬声器｜新一代超宽频X
轴马达｜磁动力弹出式肩键2.0｜前后双索尼相机｜红外Pro｜多功能NFC', 'public/imgs/phone/Redmi-k30.png', 3299, 3299, 10, 555);

update product_categoryproduct set product_picture = 'public/imgs/91bd66c0e6bf7a22d12a8b191da19078.webp' where product_id = '6';
INSERT INTO `product` VALUES (NULL, 'Xaoi Watch S1', 2, '腕事具备', '献给商务人士的高端智能手表为工作、差旅、运动健康生活助力', 'public/imgs/b8259e8fb355acf168c66f75d375c6af.webp', 1099, 1099, 10, 122);

INSERT INTO `product` VALUES (NULL, '米小米电视4A 70英寸', 4, '纤小米电视4A 70英寸', '4K超高清 | 人工智能语音系统 | 蓝牙语音遥控器', 'public/imgs/82ff5ea76730fdf6f91aba5d3b2e5739.webp', 3199, 3199, 10, 1919);
delete from product_category WHERE id = 1;

create table product_picture (
	id INT NOT NULL auto_increment,
    product_id INT NOT NULL,
    product_picture VARCHAR(200) NOT NULL,
    intro VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
select * from product_category;
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.47346782.png', 'no intro');
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.43667057.png', 'no intro');
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.45871364.png', 'no intro');
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.53886043.png', 'no intro');
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.46638970.png', 'no intro');
INSERT INTO product_picture VALUES (NULL, 2, 'public/imgs/pms_1640680536.47346782.png', 'no intro');

INSERT INTO product_category VALUES (NULL, 3, '家电');
INSERT INTO product_category VALUES (NULL, 4, '电视');
INSERT INTO product_category VALUES (NULL, 5, '耳机');
INSERT INTO product_category VALUES (NULL, 6, '手表/手环');
INSERT INTO product_category VALUES (NULL, 6, '手表/手环');
INSERT INTO product_category VALUES (NULL, 6, '手表/手环');
INSERT INTO product_category VALUES (NULL, 6, '手表/手环');
INSERT INTO product_category VALUES (NULL, 6, '手表/手环');

CREATE TABLE `seckill_product` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`seckill_id`)
) 
