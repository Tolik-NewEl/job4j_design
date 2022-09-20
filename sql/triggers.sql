create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

create or replace function tax_before()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
    before insert on products
    for each statement
    execute procedure tax_before();

create function price_history()
	returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
		values (new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_history
	before insert on products
	for each row
	execute procedure price_history();