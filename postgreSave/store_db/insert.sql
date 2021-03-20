insert into devices(name, price) values('PC', 50000);
insert into devices(name, price) values('Printer', 3200.50);
insert into devices(name, price) values('Keyboard', 5000);
insert into devices(name, price) values('Headphones', 2000.50);

insert into people(name) values('Boris');
insert into people(name) values('Ivan');
insert into people(name) values('Olga');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(3, 3);
insert into devices_people(device_id, people_id) values(4, 3);
