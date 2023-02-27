select AVG(e1.reports_to_avg) AS average_reports_to, 
	COUNT(*) AS num_higher_reports_to
from employees e
join (
  select AVG(reports_to) AS reports_to_avg
  from employees
) e1 
on e.reports_to > e1.reports_to_avg;
