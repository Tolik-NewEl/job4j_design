create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values
	('Samsung A1', 2.5),
	('Xiaomi Redmi 9', 3.2),
	('Iphone SE', 4.1),
	('Iphone 11', 6.2),
	('Honor 8', 3.7);

insert into people (name) values ('Анна'), ('Сергей'), ('Василий'), ('Петр');

insert into devices_people (device_id, people_id) values
	(1, 2), (2, 1), (3, 4), (4, 2), (5, 3), (3, 1), (2, 1);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 4;