select * 
from(
select * 
from orders )

SELECT x.name, MAX(x.count)
FROM (
 SELECT "name", count(*)
  FROM "users" INNER JOIN "microposts" ON "microposts"."user_id" = "users"."id"
  GROUP BY users.id
) x
GROUP BY x.name  