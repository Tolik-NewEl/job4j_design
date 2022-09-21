create or replace procedure delete_data_by_id (d_id integer)
	language 'plpgsql'
as $$
    BEGIN
    delete from products
	where id = d_id;
    END
$$;

create or replace function delete_then_count_less (d_count integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from products
		where count <= d_count;
    end;
$$;