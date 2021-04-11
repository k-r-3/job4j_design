drop schema public cascade;
create schema public;

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
	name varchar(50)
);

create table meetings_users(
	id serial primary key,
	meeting_id int references meeting(id),
	user_id int references users(id),
	status_id int references status(id)
);