select c.name, b.name, e.displacement, t.name from car c 
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id;

select * from car c left join body b on c.body_id <> b.id;

select * from car c left join engine e on c.engine_id <> e.id;

select * from car c left join transmission t on c.transmission_id <> t.id