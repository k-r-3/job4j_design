create table users(
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id) 
);