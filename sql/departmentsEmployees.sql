create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments (name) values ('Администрация'),
	('Отдел кадров'), ('Бухгалтерия'), ('Производство'), ('СБ');

insert into employees (name, departments_id) values
	('Сотрудник 1', 4), ('Сотрудник 2', 3),
	('Сотрудник 3', 2), ('Сотрудник 4', 1),
	('Сотрудник 5', 3), ('Сотрудник 6', 4),
	('Сотрудник 7', 1), ('Сотрудник 8', 2),
	('Сотрудник 9', 1), ('Сотрудник 10', 3),
	('Сотрудник 11', 4), ('Сотрудник 12', 2),
	('Сотрудник 13', 3), ('Сотрудник 14', 1),
	('Сотрудник 15', 2), ('Сотрудник 16', 4),
	('Сотрудник 17', 4), ('Сотрудник 18', 4),
	('Сотрудник 19', 3), ('Сотрудник 20', 2),
	('Сотрудник 21', 4), ('Сотрудник 22', 4),
	('Сотрудник 23', 4), ('Сотрудник 24', 4);

select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on e.departments_id = d.id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;

select * from departments d left join employees e on d.id = e.departments_id
where e.departments_id is null;

select e.name as Employee, d.name as Department from employees e left join departments d
on d.id = e.departments_id;
select e.name as Employee, d.name as Department from departments d right join employees e
on d.id = e.departments_id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens (name, gender) values ('Анатолий', 'Male');
insert into teens (name, gender) values ('Екатерина', 'Female');
insert into teens (name, gender) values ('Анна', 'Female');
insert into teens (name, gender) values ('Елена', 'Female');
insert into teens (name, gender) values ('Юлия', 'Female');
insert into teens (name, gender) values ('Сергей', 'Male');
insert into teens (name, gender) values ('Максим', 'Male');
insert into teens (name, gender) values ('Андрей', 'Female');
insert into teens (name, gender) values ('Петр', 'Male');
insert into teens (name, gender) values ('Антон', 'Male');

select t.name as Teen1, t.gender as Gender1, tt.name as Teen2, tt.gender from teens t cross join teens tt
where t.gender != tt.gender;
