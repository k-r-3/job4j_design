create table status(
	id serial primary key not null,
	status text check (status = 'confirm' or status = 'denied')
);

create table meeting(
	id serial primary key,
	name varchar(50)
);

create table users(
	id serial primary key,
	name varchar(50),
	meeting int references meeting(id),
	status int references status(id) default 2 
);