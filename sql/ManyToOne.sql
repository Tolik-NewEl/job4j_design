create table teacher(
	id serial primary key,
	name varchar(255)
);

create table students(
	id serial primary key,
	name varchar(255),
	teacher_id int references teacher(id)
);

insert into teacher(name) values ('Ivanov Ivan');
insert into students(name, teacher_id) values ('Petr', 1);

select * from students;
select * from teacher where id in (select id from students);