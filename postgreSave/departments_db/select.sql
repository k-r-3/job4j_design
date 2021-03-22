select d.name, e.name from departments d left join emploees e on d.emploees_id = e.id; 
select e.name, d.name from departments d right join emploees e on d.emploees_id = e.id;
select * from departments d full join emploees e on d.emploees_id = e.id; 
select * from departments d cross join emploees e;

select * from departments d left join emploees e on d.emploees_id = e.id 
where d.emploees_id is null;

select * from emploees e right join departments d on d.emploees_id = e.id;

select * from teens m cross join teens f
where not m.gender = f.gender

