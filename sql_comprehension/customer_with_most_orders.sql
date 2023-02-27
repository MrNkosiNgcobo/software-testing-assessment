select x.name, x.count
from (
  select c.contact_name as name, count(*) as count
  from orders o inner join customers c on o.customer_id = c.customer_id
  group by c.customer_id
) x
inner join (
  select MAX(count) as max_count
  from (
    select count(*) as count
    from orders
    group by customer_id
  ) y
) z
on x.count = z.max_count
