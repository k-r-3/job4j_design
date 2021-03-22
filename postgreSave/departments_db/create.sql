DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
create table emploees(
	id serial primary key,
	name varchar(255)
);

create table departments(
	id serial primary key,
	name varchar(255),
	emploees_id int references emploees(id) unique
);

create table teens(
id serial primary key,
	name varchar(255),
	gender varchar(1)
);