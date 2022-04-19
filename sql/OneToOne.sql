create table vin(
    id serial primary key,
    vin int
);

create table car(
    id serial primary key,
    model varchar(255),
	reg_number varchar(255),
    vin_id int references vin(id) unique
);