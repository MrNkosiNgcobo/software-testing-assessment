select distinct sum(b.Subtotal), 
    extract(year from a.shipped_date) as Year
from Orders a 
inner join
(
	-- Get subtotal for each order
	select distinct order_id, 
	    sum(unit_price * Quantity * (1 - Discount)) as Subtotal
	from order_details
	group by order_id    
) b on a.order_id = b.order_id
where a.shipped_date is not null
    and a.shipped_date > '1995-12-31' 
group by year
order by Year;

