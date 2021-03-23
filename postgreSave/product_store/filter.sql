select t.name, p.name from type as t join product p on t.id = p.type_id;

select p.name from type t join product p on p.type_id = t.id
where t.name = 'сыр';

select p.name from product p where upper(p.name) like upper('%мороженное%');

select * from product p
where date_part('month', p.expired_date) = date_part('month', current_date + interval '1 month')

select * from product order by product.price desc 
limit 1;

select t.name, count(p.type_id) from type t join product p on t.id = p.type_id
group by t.name;

select * from product p join type t on t.id = p.type_id
where t.name = 'сыр' or t.name = 'молочный продукт';

select count(p.name), p.name from product p
group by p.name
having count(p.name) < 10;

select t.name, p.name, p.price, p.expired_date from type t join product p on t.id = p.type_id
