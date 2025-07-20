CREATE OR REPLACE TRIGGER BankTr
AFTER INSERT ON transaction
FOR EACH ROW
WHEN (NEW.TRANS_TYPE = 'deposit')
BEGIN
    UPDATE customer
    SET balance = balance + :NEW.amount
    WHERE cust_id = :NEW.cust_id;
END;
/


CREATE OR REPLACE PROCEDURE prcCursorShow 
AS
   CURSOR cursor_books IS 
      SELECT book_id, title, author, publisher, price 
      FROM Books
      WHERE type = 'IT';

   v_book_id    Books.book_id%TYPE;
   v_title      Books.title%TYPE;
   v_author     Books.author%TYPE;
   v_publisher  Books.publisher%TYPE;
   v_price      Books.price%TYPE;
BEGIN
   OPEN cursor_books;
   LOOP
      FETCH cursor_books INTO v_book_id, v_title, v_author, v_publisher, v_price;
      EXIT WHEN cursor_books%NOTFOUND;

      DBMS_OUTPUT.PUT_LINE('--------------------------------');
      DBMS_OUTPUT.PUT_LINE('Book ID     : ' || v_book_id);
      DBMS_OUTPUT.PUT_LINE('Title       : ' || v_title);
      DBMS_OUTPUT.PUT_LINE('Author      : ' || v_author);
      DBMS_OUTPUT.PUT_LINE('Publisher   : ' || v_publisher);
      DBMS_OUTPUT.PUT_LINE('Price       : ' || v_price);
      DBMS_OUTPUT.PUT_LINE('--------------------------------');
   END LOOP;
   CLOSE cursor_books;
END;
/
