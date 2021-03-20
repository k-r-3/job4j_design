create table comments(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);