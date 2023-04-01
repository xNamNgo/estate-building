use exercise3;

-- select
select * from building;
select * from rent_area;
select * from assignment_building;

-- buliding
INSERT INTO `building` VALUES 
(1,NULL,NULL,'admin','2023-03-12 15:19:26',NULL,'','','','','QUAN_1','',500,'','','','Ngô Hoàng Nam','','Nam Giao Building Tower','',2,'','','0935693144,0901176685',15,'15 triệu/m2','','','59 phan xích long','','TANG_TRET,NGUYEN_CAN','Phường 2'),
(2,NULL,NULL,'admin','2023-03-12 15:31:54',NULL,'','','','','QUAN_2','',650,'','','','Nguyễn Văn A ','','ACM Tower','',2,'','','0953633215',18,'18 triệu/m2','','','96 cao thắng','','NGUYEN_CAN','Phường 4'),
(3,NULL,NULL,'admin','2023-03-12 15:32:23',NULL,'','','','','QUAN_1','',200,'','','','Phạm Văn B ','','Alpha 2 Building Tower','',1,'','','0935468632',20,'20 triệu/m2','','','153 nguyễn đình chiểu','','NOI_THAT','Phường 6'),
(4,NULL,NULL,'admin','2023-03-12 13:02:56',NULL,'','','','','QUAN_4','',200,'','','','Lê Thị G','','IDD 1 Building','',1,'','','0915423753',12,'12 triệu/m2','','','111 Lý Chính Thắng','','NOI_THAT,TANG_TRET,NGUYEN_CAN','Phường 7')

-- rent_area
INSERT INTO `rent_area` VALUES
(184,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',250,4),
(185,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',300,4),
(186,'admin','2023-03-12 13:02:56','admin','2023-03-12 13:02:56',400,4),
(190,'admin','2023-03-12 15:19:26','admin','2023-03-12 15:19:26',100,1),
(191,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',300,2),
(192,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',400,2),
(193,'admin','2023-03-12 15:31:54','admin','2023-03-12 15:31:54',500,2),
(194,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',1000,3),
(195,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',200,3),
(196,'admin','2023-03-12 15:32:23','admin','2023-03-12 15:32:23',523,3);

-- user
INSERT INTO `user` VALUES 
(1,NULL,NULL,NULL,NULL,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvana'),
(2,NULL,NULL,NULL,NULL,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanb'),
(3,NULL,NULL,NULL,NULL,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanc'),
(4,NULL,NULL,NULL,NULL,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvand'),
(5,NULL,NULL,NULL,NULL,NULL,'admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'admin');

-- role
INSERT INTO `role` VALUES
(1,NULL,NULL,NULL,NULL,'MANAGER','Quản lý'),
(2,NULL,NULL,NULL,NULL,'STAFF','Nhân viên');

-- user_role
INSERT INTO `user_role` VALUES (5,1),(1,2),(2,2),(3,2),(4,2);

INSERT INTO `assignment_building` VALUES 
(1,2),
(3,2),
(1,3),
(4,3);
