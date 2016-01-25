CREATE OR REPLACE TRIGGER AfterDeleteonStudents
AFTER DELETE
ON students
FOR EACH ROW  
DECLARE
v_who varchar2(30);
v_time date;
v_table_name varchar2(30);
v_operation varchar2(30);
v_key_value varchar2(30);
BEGIN
delete from enrollments where sid = :old.sid;
select user into v_who from dual;
select sysdate into v_time from dual;
v_table_name := 'Students';
v_operation := 'Delete';
v_key_value := :old.sid;

insert into logs (logid,who,time,table_name,operation,key_value)
values 
(new_log.NEXTVAL,v_who,v_time,v_table_name,v_operation,v_key_value);
END;
