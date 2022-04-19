create table students(
     id serial primary key,
     name varchar(255)
 );
 
 create table teachers(
     id serial primary key,
     name varchar(255)
 );
 
 create table teachers(
     id serial primary key,
     student_id int references students(id),
     teacher_id int references teachers(id)
 );