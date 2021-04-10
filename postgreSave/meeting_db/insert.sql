insert into status(status) values('confirm');
insert into status(status) values('denied');

insert into meeting(name) values('meeting1');
insert into meeting(name) values('meeting2');
insert into meeting(name) values('meeting3');
insert into meeting(name) values('meeting4');
insert into meeting(name) values('meeting5');

insert into users(name, meeting, status)  values('Ivan', 1, 1);
insert into users(name, meeting, status)  values('Ivan', 2, 1);
insert into users(name, meeting)  values('Ivan', 3);
insert into users(name, meeting)  values('Boris', 1);
insert into users(name, meeting, status)  values('Boris', 2, 1);
insert into users(name, meeting, status)  values('Boris', 3, 1);