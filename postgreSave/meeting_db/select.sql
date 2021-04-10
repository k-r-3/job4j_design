select s.status, m.name, count(u.name) from status s left join users u on s.id = u.status
join meeting m on m.id = u.meeting where s.status = 'confirm'
group by s.status,m.name;

select m.name from meeting m left join users u on m.id = u.meeting 
where u.name is null;