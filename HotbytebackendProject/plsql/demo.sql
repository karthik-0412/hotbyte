SET SERVEROUTPUT ON;

DECLARE
    Age NUMBER(2) := 23;
    Vname VARCHAR2(20) := 'Karthik Athreya';
    Dept VARCHAR(20) := 'CS';
BEGIN
    DBMS_OUTPUT.PUT_LINE('Name is ' || Vname || 'Age is'|| Age ||'Dept is '|| Dept);
END;
/
