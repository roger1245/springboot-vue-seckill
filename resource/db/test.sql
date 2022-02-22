select * from product;
INSERT INTO `product` VALUES (NULL, 'Redmi K50', 1, '全线拉满的冷血旗舰', '「新品热卖中！至高享24期免息」骁龙8 年度旗舰｜「双VC」强劲散热｜以电竞
标准打造｜120W神仙秒充｜OLED 柔性直屏｜JBL 宽频四单元扬声器｜新一代超宽频X
轴马达｜磁动力弹出式肩键2.0｜前后双索尼相机｜红外Pro｜多功能NFC', 'public/imgs/phone/Redmi-k30.png', 3299, 3299, 10, 555);

update product set product_picture = 'public/imgs/91bd66c0e6bf7a22d12a8b191da19078.webp' where product_id = '6';
INSERT INTO `product` VALUES (NULL, 'Xaoi Watch S1', 2, '腕事具备', '献给商务人士的高端智能手表为工作、差旅、运动健康生活助力', 'public/imgs/b8259e8fb355acf168c66f75d375c6af.webp', 1099, 1099, 10, 122);

INSERT INTO `product` VALUES (NULL, '米家超薄电磁炉', 3, '纤薄美学，精准猛火', '23mm纤薄机身，易收纳 / 2100W爆炒功率 / 100W低功率持续加热 / 99挡细调火力 / NFC贴一下，米家APP即可闪现

', 'public/imgs/82ff5ea76730fdf6f91aba5d3b2e5739.webp', 499, 499, 10, 999);

UPDATE product set product_picture = 'public/imgs/1061cd8d46977b5291d481d0cd44105e.webp' where product_name = 'Redmi K50';