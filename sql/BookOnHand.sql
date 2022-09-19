create view books_on_hand as
    select s.name Имя_Студента, b.name Название_книги, a.name Автор from students s
    join orders o on s.id = o.student_id
    join books b on o.book_id = b.id
    join authors a on b.author_id = a.id
    group by (s.name, a.name, b.name);

select * from books_on_hand;