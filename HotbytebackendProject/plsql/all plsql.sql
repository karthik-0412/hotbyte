set serveroutput on;

declare
salary number:= &salary;
bonus number:= &bonus;
totalsalary number;
begin
totalsalary:= salary+salary*bonus/100;
dbms_output.put_line('Total salary of employee is  ' || totalsalary);

end;
/


set serveroutput on;

declare

user varchar(20):= '&user';
password varchar(30):= '&password';

begin 
if user = 'karthik' and password = 'athreya' then 
	DBMS_OUTPUT.PUT_LINE('Validation successful' );
else
	DBMS_OUTPUT.PUT_LINE('Validation failed' );
end if;
end;
/

set serveroutput on;
 
 
declare
 
n number:=&enterANo;

i number;
fact number:= 1;

begin
 
for i in 1..n Loop
fact:= fact *i;
 

end loop;
DBMS_OUTPUT.PUT_LINE( 'Factorial of '||n||' is '|| fact);

end;

/
 

SET SERVEROUTPUT ON;

DECLARE
  n        NUMBER := &enterANumber;
  i        NUMBER;
  is_prime BOOLEAN := TRUE;
BEGIN
  IF n <= 1 THEN
    DBMS_OUTPUT.PUT_LINE(n || ' is not a prime number');
  ELSE
    FOR i IN 2 .. n / 2 LOOP
      IF MOD(n, i) = 0 THEN
        is_prime := FALSE;
        EXIT;
      END IF;
    END LOOP;

    IF is_prime THEN
      DBMS_OUTPUT.PUT_LINE(n || ' is a prime number');
    ELSE
      DBMS_OUTPUT.PUT_LINE(n || ' is not a prime number');
    END IF;
  END IF;
END;
/


SET SERVEROUTPUT ON;

DECLARE
  v_roll NUMBER := &Roll;
  v_fee  NUMBER;
BEGIN
  SELECT fee INTO v_fee
  FROM student
  WHERE roll = v_roll;

  IF v_fee > 200 THEN
    UPDATE student SET fee = fee + 100 WHERE roll = v_roll;
    v_fee := v_fee + 100;
  ELSE
    UPDATE student SET fee = fee + 200 WHERE roll = v_roll;
    v_fee := v_fee + 200;
  END IF;

  DBMS_OUTPUT.PUT_LINE(v_roll || ' ' || v_fee);
END;
/


SET SERVEROUTPUT ON;

DECLARE
  v_book_name   VARCHAR2(50) := '&EnterBookName';
  v_price       NUMBER;
  v_category    VARCHAR2(10);
  v_discount    NUMBER;
BEGIN
  SELECT price, category INTO v_price, v_category
  FROM books
  WHERE name = v_book_name;

  IF v_category = 'IT' THEN
    v_discount := v_price * 0.05;
  ELSIF v_category = 'Non-IT' THEN
    v_discount := v_price * 0.10;
  ELSE
    v_discount := 0;
  END IF;

  DBMS_OUTPUT.PUT_LINE('Book: ' || v_book_name);
  DBMS_OUTPUT.PUT_LINE('Category: ' || v_category);
  DBMS_OUTPUT.PUT_LINE('Original Price: ' || v_price);
  DBMS_OUTPUT.PUT_LINE('Discount: ' || v_discount);
  DBMS_OUTPUT.PUT_LINE('Final Price: ' || (v_price - v_discount));
END;
/



SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE CalcTotalSalary (
    salary        IN  NUMBER,
    total_salary  OUT NUMBER
) AS
  bonus NUMBER;
BEGIN
  IF salary <= 5000 THEN
    bonus := salary * 0.05;
  ELSE
    bonus := salary * 0.10;
  END IF;

  total_salary := salary + bonus; 
END;
/


DECLARE
  input_salary   NUMBER := &EnterSalary;
  result_salary  NUMBER;
BEGIN
  CalcTotalSalary(input_salary, result_salary);
  DBMS_OUTPUT.PUT_LINE('Total Salary including bonus: ' || result_salary);
END;
/
