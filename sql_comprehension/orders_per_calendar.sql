select distinct date(a.shipped_date) as shipped_date, 
    a.order_id, 
    b.Subtotal, 
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
    and a.shipped_date between '1996-12-24' and '1997-09-30'
order by a.shipped_date;

