create table house(
	id serial primary key,
	address varchar(255),
	storey int
);

create table families(
	id serial primary key,
	lastName varchar(255),
	house_id int references house(id)
);

insert into house(address, storey) values('Lenina st.', 1);
insert into house(address, storey) values('Central st.', 2);
insert into house(address, storey) values('New st.', 4);
insert into families(lastName, house_id) values('Ivanov', 1);
insert into families(lastName, house_id) values('Petrov', 2);
insert into families(lastName, house_id) values('Sidorov', 3);