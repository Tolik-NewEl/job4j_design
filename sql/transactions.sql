create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 20, 25);
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 32, 74);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 1, 75);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 11, 64);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 26, 105);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 50);

begin transaction;
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 6, 500);
delete from products where name = 'product_1';
savepoint first_sp;
select * from products;
delete from products where name = 'product_2';
delete from products where name = 'product_3';
savepoint second_sp;
select * from products;
rollback to savepoint first_sp;
select * from products;
rollback to savepoint second_sp;
rollback;
select * from products;

begin transaction;
insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 6, 500);
savepoint first_sp;
delete from products where name = 'product_3';
delete from products where name = 'product_2';
delete from products where name = 'product_1';
release savepoint first_sp;
rollback to savepoint second_sp;
rollback;
select * from products;