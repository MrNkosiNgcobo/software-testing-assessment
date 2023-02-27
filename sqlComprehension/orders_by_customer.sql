select count(*) 
from orders o 
inner join customers c on o.customer_id = c.customer_id 
inner join shippers s on s.shipper_id = o.ship_via 
where c.company_name = 'Simons bistro'
and s.company_name = 'United Package' 

