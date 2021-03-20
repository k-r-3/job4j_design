select avg(price) from devices;
select p.name, avg(d.price) from devices_people as dp join devices d on d.id = dp.device_id  
join people p on p.id = dp.people_id
group by p.name
having avg(d.price) > 5000;