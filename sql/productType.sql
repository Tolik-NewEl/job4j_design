create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type (name) values ('СЫР'), ('РЫБА'), ('МОЛОКО');

insert into product (name, expired_date, price, type_id) values
	('Сыр плавленный', '20.05.2022', 199.0, 1),
	('Сыр моцарелла', '25.06.2022', 250.5, 1),
	('Сыр рокфор', '15.08.2022', 351.0, 1),
	('Сыр гауда', '30.04.2022', 149.0, 1),
	('Сыр тильтизер', '03.05.2022', 187.2, 1),
	('Рыба путассу', '01.06.2022', 230.0, 2),
	('Рыба щука', '03.08.2022', 199.0, 2),
	('Рыба скумбрия', '29.04.2022', 210.5, 2),
	('Мороженое пломбир', '05.05.2022', 50.0, 3),
	('Мороженое эскимо', '06.05.2022', 60.0, 3),
	('Молоко', '09.05.2022', 64.0, 3),
	('Творог', '02.05.2022', 44.0, 3);

select * from product as p
join type as t on p.type_id = t.id
where t.name = 'СЫР';

select * from product where name like '%Мороженое%';

select * from product where expired_date < current_date;

select name, max(price) from product
where price = (select max(price) from product)
group by name, price;

select t.name, count(p.type_id) from product as p
join type as t on p.type_id = t.id
group by t.name;

select * from product as p
join type as t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.type_id) from product as p
join type as t on p.type_id = t.id
group by t.name
having count(p.type_id) < 5;

select p.name, t.name from product as p
join type as t on p.type_id = t.id;