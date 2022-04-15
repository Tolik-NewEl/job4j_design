create table beer(
	id serial primary key,
	name text,
	alc real,
	price money
);
insert into beer (name, alc, price) values('IPA', '7.0', '2');
update beer set price = '3';
delete from beer;