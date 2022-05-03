create table cars (
	id serial primary key,
	model varchar(255),
	vin varchar(255)
);

create table drivers (
	id serial primary key,
	name varchar(255),
	cars_id int references cars(id)
);

insert into cars(model, vin) values ('Toyota Corolla', 'XXXCCC111BBB');
insert into cars(model, vin) values ('Mazda 5', 'CCC222BBB333');
insert into cars(model) values ('Ford Focus 2');

insert into drivers(name, cars_id) values ('Ivan', 1);
insert into drivers(name, cars_id) values ('Anatoly', 2);
insert into drivers(name, cars_id) values ('Ekaterina', 2);
insert into drivers(name) values ('Petr');

select dr.name, c.model
from drivers as dr
join cars as c
on dr.cars_id = c.id;

select c.vin, c.model, dr.name
from drivers as dr
join cars as c
on dr.cars_id = c.id;

select c.vin as VIN, c.model as Модель, dr.name as Имя
from drivers as dr
join cars as c
on dr.cars_id = c.id;


