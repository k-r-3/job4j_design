select p.name from person p where p.company_id <> 5;
select p.name, c.name from person p left join company c on p.company_id = c.id;

with data as(select c.name as cn, count(p.name) as pn from person p left join company c on p.company_id = c.id 
group by c.name)
select * from data where pn = (select max(pn) from data) 