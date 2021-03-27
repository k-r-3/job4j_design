select c.name, b.name, e.displacement, t.name from car c 
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id;

select e.displacement from engine e left join car c on c.engine_id = e.id
where c.id is null;

select b.name from body b left join car c on c.body_id = b.id
where c.id is null;

select t.name from transmission t left join car c on c.transmission_id = t.id
where c.id is null