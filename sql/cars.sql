create table body (
	id serial primary key,
	name varchar(255)
);

create table engine (
	id serial primary key,
	name varchar(255)
);

create table gearbox (
	id serial primary key,
	name varchar(255)
);

create table carr (
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id)
);

insert into body (name) values
	('Sedan'), ('Minivan'), ('Coupe'), ('Hatchback'),
	('Sedan'), ('Minivan'), ('Coupe'), ('Hatchback'),
	('Sedan'), ('Minivan'), ('Coupe'), ('Hatchback');

insert into engine (name) values
	('oil'), ('gas'), ('diesel'), ('hybrid'),
	('oil'), ('gas'), ('diesel'), ('hybrid');

insert into gearbox (name) values
	('Mechanic'), ('Automatic'),
	('Mechanic'), ('Automatic'),
	('Mechanic'), ('Automatic'),
	('Mechanic'), ('Automatic');

insert into carr (name, body_id, engine_id, gearbox_id) values
	('car1', 12, 2, 4), ('car2', 4, 1, 7),
	('car3', 1, 3, 8), ('car4', 6, 5, 5),
	('car5', 10, 2, 8), ('car6', 3, 6, 2),
	('car7', 2, 2, 5), ('car8', 5, 5, 5),
	('car9', 7, 7, 7), ('car10', 3, 2, 1);

select c.name as Model, b.name as Type, e.name as Engine, g.name as Gear from carr c
join body b on c.body_id = b.id join engine e on c.engine_id = e.id join gearbox g on c.gearbox_id = g.id;

select b.id, b.name from body b left join carr c on c.body_id = b.id where c.id is null;
select e.id, e.name from engine e left join carr c on c.engine_id = e.id where c.id is null;
select g.id, g.name from gearbox g left join carr c on c.gearbox_id = g.id where c.id is null;