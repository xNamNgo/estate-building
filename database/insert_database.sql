use exercise3
select * from building
select * from user
select * from role
select * from user_role


-- building
    INSERT INTO building (name, street, ward, district, number_of_basement, floor_area, rent_price, rent_price_description, type,manager_name,phone_number)
VALUES
    ('Nam Giao Building Tower', '59 phan xích long', 'Phường 2', 'QUAN_1', 2, 500, 15, '15 triệu/m2', 'TANG_TRET,NGUYEN_CAN','Ngô Hoàng Nam','0935693144,0901176685'),
    ('ACM Tower', '96 cao thắng', 'Phường 4', 'QUAN_2', 2, 650, 18, '18 triệu/m2', 'NGUYEN_CAN',null,null),
    ('Alpha 2 Building Tower', '153 nguyễn đình chiểu', 'Phường 6', 'QUAN_1', 1, 200, 20, '20 triệu/m2', 'NOI_THAT',null,null),
    ('IDD 1 Building', '111 Lý Chính Thắng', 'Phường 7', 'QUAN_4', 1, 200, 12, '12 triệu/m2', 'TANG_TRET,NGUYEN_CAN,NOI_THAT',null,null);

-- user
insert into user (username,password,fullname,status)
values
    ('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1),
    ('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',1),
    ('nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',1),
    ('nguyenvand','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van d',1),
    ('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1)

-- role
    insert into role(name,code)
values
    ('Quản lý','MANAGER'),
    ('Nhân viên','STAFF')

-- user_role
insert into user_role(role_id,user_id)
values
    (1,5),
    (2,1),
    (2,2),
    (2,3),
    (2,4)

-- assignment_building
insert into assignment_building(staff_id,building_id)
values
    (2,1),
    (2,3),
    (3,1),
    (3,4)


-- rent_area
insert into rent_area(value,building_id)
values
    (100,1),
    (200,1),
    (200,2),
    (300,2),
    (400,2),
    (300,3),
    (400,3),
    (500,3),
    (100,4),
    (250,4),
    (400,4)