use exercise3;
select * from building;
select * from rent_area;
select * from assignment_building;


INSERT INTO `building` VALUES 
(21,NULL,NULL,'admin','2023-04-04 20:47:02',NULL,NULL,NULL,NULL,NULL,'QUAN_4',NULL,325279,NULL,NULL,NULL,'Port Authority',NULL,'One World Trade Center',NULL,5,NULL,NULL,'08889998881',900000,NULL,NULL,NULL,'285 Fulton Street Manhattan, New York City, U.S.',NULL,'NGUYEN_CAN','Phường An Nam'),
(20,'admin','2023-04-04 20:40:49','admin','2023-04-04 20:40:49',NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,114000,NULL,NULL,NULL,'Turner International,',NULL,'Bitexco Financial Tower',NULL,3,NULL,NULL,'0999123456789',7000000,NULL,NULL,NULL,'683 Trần Quang Diệu',NULL,'NOI_THAT','Phường An Dương Vương'),
(18,NULL,NULL,'admin','2023-04-04 20:41:08',NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,300000,NULL,NULL,NULL,'Tập đoàn Keangnam',NULL,'Keangnam Hanoi Landmark Tower',NULL,2,NULL,NULL,'0988999999',50000,NULL,NULL,NULL,'700B Phạm Hùng,Hà Nội',NULL,'NOI_THAT,NGUYEN_CAN','Phường 7'),
(19,NULL,NULL,'admin','2023-04-04 20:38:35',NULL,NULL,NULL,NULL,NULL,'QUAN_2',NULL,247075,'/building/PNG_transparency_demonstration_1.png',NULL,NULL,'Callison ,DOUL Int\'l ',NULL,'Lotte Center Hà Nội',NULL,2,NULL,NULL,'012223999999',3260000,NULL,NULL,NULL,'54 Liễu Giai, Hà Nội, Việt Nam',NULL,'NGUYEN_CAN','Phường 35'),
(17,NULL,NULL,'admin','2023-04-04 20:33:47',NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,1000,'/building/images.png','S',NULL,'Phạm Nhật Vượng',NULL,'Landmark 81',NULL,NULL,NULL,NULL,'012345678999',2000000,NULL,NULL,NULL,'720A Điện Biên Phủ,',NULL,'NOI_THAT,TANG_TRET,NGUYEN_CAN','Phường 22');

INSERT INTO `rent_area` VALUES
 (258,'admin','2023-04-04 20:40:49','admin','2023-04-04 20:40:49',10800,20),
 (257,'admin','2023-04-04 20:40:49','admin','2023-04-04 20:40:49',5999,20),
 (256,'admin','2023-04-04 20:40:49','admin','2023-04-04 20:40:49',5932,20),
 (238,'admin','2023-04-04 20:33:47','admin','2023-04-04 20:33:47',896,17),
 (237,'admin','2023-04-04 20:33:47','admin','2023-04-04 20:33:47',600,17),
 (255,'admin','2023-04-04 20:40:49','admin','2023-04-04 20:40:49',5000,20),
 (236,'admin','2023-04-04 20:33:47','admin','2023-04-04 20:33:47',300,17),
 (251,'admin','2023-04-04 20:38:35','admin','2023-04-04 20:38:35',6000,19),
 (252,'admin','2023-04-04 20:38:35','admin','2023-04-04 20:38:35',4599,19),
 (253,'admin','2023-04-04 20:38:35','admin','2023-04-04 20:38:35',4363,19),
 (254,'admin','2023-04-04 20:38:35','admin','2023-04-04 20:38:35',3364,19),
 (259,'admin','2023-04-04 20:41:08','admin','2023-04-04 20:41:08',5964,18),
 (260,'admin','2023-04-04 20:41:08','admin','2023-04-04 20:41:08',4932,18),
 (261,'admin','2023-04-04 20:41:08','admin','2023-04-04 20:41:08',3065,18),
 (262,'admin','2023-04-04 20:41:08','admin','2023-04-04 20:41:08',7044,18),
 (269,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',782,21),
 (270,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',734,21),
 (271,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',765,21),
 (272,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',693,21),
 (273,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',9000,21),
 (274,'admin','2023-04-04 20:47:02','admin','2023-04-04 20:47:02',1000,21);

INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'MANAGER','Quản lý'),(2,NULL,NULL,NULL,NULL,'STAFF','Nhân viên');

INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvana'),(2,NULL,NULL,NULL,NULL,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanb'),(3,NULL,NULL,NULL,NULL,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanc'),(4,NULL,NULL,NULL,NULL,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvand'),(5,NULL,NULL,NULL,NULL,NULL,'admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'admin');

INSERT INTO `user_role` VALUES (5,1),(1,2),(2,2),(3,2),(4,2);

INSERT INTO `assignment_building` VALUES (20,2),(18,3),(21,1),(19,4),(17,2);
