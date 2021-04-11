select m.name, count(u.name) from meeting m left join meetings_users m_u on m.id = m_u.meeting_id
left join users u on u.id = m_u.user_id
where m_u.status_id = 1
group by m.name;

select m.name from meeting m left join meetings_users m_u on m.id = m_u.meeting_id
where m_u.status_id = 2
group by m.name
having count(m_u.status_id) = (select count(u.name) from users u)