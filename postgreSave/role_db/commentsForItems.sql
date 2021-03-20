create table commentsForItems(
	id serial primary key,
	items_id int references item(id),
	comments_id int references comments(id)
);