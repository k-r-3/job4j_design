insert into body(name) values('Sedan');
insert into body(name) values('Hatchback');
insert into body(name) values('Estate wagon');
insert into body(name) values('Coupe');

insert into engine(displacement) values(1.2);
insert into engine(displacement) values(1.5);
insert into engine(displacement) values(2.0);
insert into engine(displacement) values(1.0);

insert into transmission(name) values('Manual');
insert into transmission(name) values('Automatic');
insert into transmission(name) values('Variable');
insert into transmission(name) values('Dual-Clutch');

insert into car(name, body_id, engine_id, transmission_id) values('BMW 5', 1, 2, 2);
insert into car(name, body_id, engine_id, transmission_id) values('BMW X1', 2, 3, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Volkswagen Polo', 1, 1, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Volkswagen Passat', 3, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Toyota Camry', 1, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Toyota Corolla', 3, 1, 2);
