insert into status(status) values('confirm');
insert into status(status) values('denied');

insert into meeting(name) values('meeting1');
insert into meeting(name) values('meeting2');
insert into meeting(name) values('meeting3');
insert into meeting(name) values('meeting4');
insert into meeting(name) values('meeting5');

insert into users(name)  values('Ivan');
insert into users(name)  values('Boris');

insert into meetings_users(meeting_id, user_id, status_id) values(1, 1, 1);
insert into meetings_users(meeting_id, user_id, status_id) values(1, 2, 1);
insert into meetings_users(meeting_id, user_id, status_id) values(2, 1, 2);
insert into meetings_users(meeting_id, user_id, status_id) values(2, 2, 1);
insert into meetings_users(meeting_id, user_id, status_id) values(3, 1, 2);
insert into meetings_users(meeting_id, user_id, status_id) values(3, 2, 2);
insert into meetings_users(meeting_id, user_id, status_id) values(4, 1, 1);
insert into meetings_users(meeting_id, user_id, status_id) values(4, 2, 2);
insert into meetings_users(meeting_id, user_id, status_id) values(5, 1, 2);
insert into meetings_users(meeting_id, user_id, status_id) values(5, 2, 2);