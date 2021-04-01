select p.name, c.name, c.id as company_id from person p join company c on p.company_id = c.id 
where p.company_id <> 5;

with data as(select c.name as company_name, count(p.name) as employees_amount from person p left join company c on p.company_id = c.id 
group by c.name)
select * from data where employees_amount = (select max(employees_amount) from data) 