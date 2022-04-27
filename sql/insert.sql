insert into role(name) values('admin');
insert into role(name) values('user');

insert into rules(name) values('create');
insert into rules(name) values('edit');
insert into rules(name) values('delete');
insert into rules(name) values('comment');

insert into category(name) values('regular');
insert into category(name) values('important');

insert into state(name) values('new');
insert into state(name) values('in progress');
insert into state(name) values('finished');

insert into users(name, role_id) values('Ivan', 1);
insert into users(name, role_id) values('Petr', 2);

insert into item(name, users_id, category_id, state_id) values('item1', 1, 2, 1);
insert into item(name, users_id, category_id, state_id) values('item2', 1, 1, 2);

insert into comments(name, item_id) values('emergency!', 1);
insert into comments(name, item_id) values('need more info', 2);

insert into attachs(name, size, item_id) values('att1', 20, 1);