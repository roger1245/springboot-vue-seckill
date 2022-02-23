select * from product;
INSERT INTO `product` VALUES (NULL, 'Redmi K50', 1, '全线拉满的冷血旗舰', '「新品热卖中！至高享24期免息」骁龙8 年度旗舰｜「双VC」强劲散热｜以电竞
标准打造｜120W神仙秒充｜OLED 柔性直屏｜JBL 宽频四单元扬声器｜新一代超宽频X
轴马达｜磁动力弹出式肩键2.0｜前后双索尼相机｜红外Pro｜多功能NFC', 'public/imgs/phone/Redmi-k30.png', 3299, 3299, 10, 555);

update product set product_picture = 'public/imgs/91bd66c0e6bf7a22d12a8b191da19078.webp' where product_id = '6';
INSERT INTO `product` VALUES (NULL, 'Xaoi Watch S1', 2, '腕事具备', '献给商务人士的高端智能手表为工作、差旅、运动健康生活助力', 'public/imgs/b8259e8fb355acf168c66f75d375c6af.webp', 1099, 1099, 10, 122);

INSERT INTO `product` VALUES (NULL, '米小米电视4A 70英寸', 4, '纤小米电视4A 70英寸', '4K超高清 | 人工智能语音系统 | 蓝牙语音遥控器', 'public/imgs/82ff5ea76730fdf6f91aba5d3b2e5739.webp', 3199, 3199, 10, 1919);
delete from product WHERE product_id between 16 AND 31;

create table product_category (
	id INT NOT NULL auto_increment,
    category_id INT NOT NULL,
    category_name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
