select m.name, count(u.name) from meeting m left join meetings_users m_u on m.id = m_u.meeting_id
left join users u on u.id = m_u.user_id
where m_u.status_id = 1
group by m.name;

with data as (select m.name n from meeting m join meetings_users m_u on m.id = m_u.meeting_id
where m_u.status_id = 1)
select m.name from meeting m left join data d on m.name = d.n 
where d.n is null

