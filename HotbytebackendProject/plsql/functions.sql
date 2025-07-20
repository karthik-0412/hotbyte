create or replace function calToal(salary in number)
return number
as
 
    bonus number;
 
    total_salary number;
 
begin
 
    if salary <= 5000 then
 
        bonus := salary * 0.05;
 
    elsif salary > 5000 then
 
        bonus := salary * 0.10;
 
    else
 
        bonus := 0;
 
    end if;
    total_salary := salary + bonus;

  return   total_salary;
end;
 
/

create or replace procedure totsal(
 
salary  in number,  
bonus out number ,
total out number) 
as
begin
bonus:=salary*0.05;
total:=bonus+salary;
end;
/

 
declare
 
bonus number;
 
total number;
s number :=5000;
begin
 
totsal(s,bonus,total);
 
dbms_output.put_line('Total salary: '||total);
dbms_output.put_line('Bonus: '||bonus);
 
end;
 
/


create or replace procedure vak(
    dept in varchar2,
    salary out number
)
as
begin
    if dept = 'sales' then
        salary := 20000;
    elsif dept = 'admin' then
        salary := 30000;
    elsif dept = 'it' then
        salary := 50000;
    else
        salary := 10000;
    end if;
end;
/

declare
    dept varchar2(30) := '&dept';
    salary number;
begin
    vak(dept, salary);
    dbms_output.put_line('salary: ' || salary);
end;
/
