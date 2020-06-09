select d.name as "Department", e.Name as "Employee", e.Salary as "Salary"
from Employee e inner join Department d on e.DepartmentId = d.Id 
where (Select count(Distinct m.Salary) from Employee m where m.DepartmentId = e.DepartmentId and m.Salary > e.Salary) < 3;