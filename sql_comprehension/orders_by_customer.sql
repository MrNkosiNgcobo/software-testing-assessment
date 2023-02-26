select count(*) from orders o 
inner join customers c on o.customer_id = c.customer_id 
where c.company_name = 'Simons bistro'