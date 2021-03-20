insert into rules(id, name) values(1, 'Some rule');
insert into roles(id, name, rules_id) values(1, 'Plowman', 1);
insert into users(id, name, roles_id) values(1, 'Ivan', 1);
insert into category(id, name) values(1, 'credit');
insert into state(id, name) values(1, 'approved');
insert into item(id, name, users_id, category_id, state_id) values(1, 'request for a loan', 1, 1, 1);
insert into comments(name, item_id) values('Some comment', 1);
insert into attachs(name, item_id) values('Some attachs', 1);