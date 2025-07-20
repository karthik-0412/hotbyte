CREATE OR REPLACE PROCEDURE ValidAge(age IN NUMBER) AS
BEGIN
  IF age >= 18 THEN
    DBMS_OUTPUT.PUT_LINE('Valid');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Not valid');
  END IF;
END;
/

SET SERVEROUTPUT ON;

DECLARE
  input_salary   NUMBER := &EnterSalary;
  result_salary  NUMBER;
BEGIN
  CalcTotalSalary(input_salary, result_salary);
  DBMS_OUTPUT.PUT_LINE('Total Salary including bonus: ' || result_salary);
END;
/

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
