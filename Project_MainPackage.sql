
CREATE OR REPLACE package Project_MainPackage AS

type ref_cursor1 is ref cursor;
type ref_cursor2 is ref cursor;
type ref_cursor3 is ref cursor;
type ref_cursor4 is ref cursor;
type ref_cursor5 is ref cursor;
type ref_cursor6 is ref cursor;
type ref_cursor8 is ref cursor;
TYPE ref_cursor7 IS ref cursor;

TYPE ref_cursor9 IS ref cursor;

TYPE ref_cursor10 IS ref cursor;

TYPE nocourse IS ref cursor;

TYPE v_course IS ref cursor;

function show_students return ref_cursor1;
function show_courses return ref_cursor2;
function show_prerequisites return ref_cursor3;
function show_classes return ref_cursor4;
function show_enrollments return ref_cursor5;
function show_logs return ref_cursor6;
function prerequisite_courses(c_dept_code in prerequisites.dept_code%type , c_course# in prerequisites.course#%type) return ref_cursor7;
function displaystudents(c_sid IN enrollments.sid%type) return ref_cursor8;

procedure update_students(c_sid IN students.sid%type,c_firstname IN students.firstname%type ,c_lastname IN students.lastname%type,c_status IN students.status%type,
c_gpa IN students.gpa%type ,c_email IN students.email%type);

function prerequisite_courses(c_dept_code in prerequisites.dept_code%type , c_course# in prerequisites.course#%type) return ref_cursor9;

function classstudents(c_cid IN enrollments.classid%type) return ref_cursor10;


procedure enroll_student(s_sid IN students.sid%type,c_cid  IN classes.classid%type);
procedure drop_student(s_sid IN students.sid%type,c_cid  IN classes.classid%type);
procedure delete_student(s_sid IN students.sid%type);

function nocourses(s_sid IN students.sid%type) return nocourse;

function validcourses(s_classid IN classes.classid%type) return v_course;



END;
/


CREATE OR REPLACE package body Project_MainPackage as

function show_students 
return ref_cursor1 as

    rc ref_cursor1;	

BEGIN
   	open rc for
        select * from students;
        return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function show_courses 
return ref_cursor2 AS
	rc ref_cursor2;
   
BEGIN

   open rc for
        select * from courses;
        return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function show_prerequisites 
return ref_cursor3 as
	rc ref_cursor3;
   
BEGIN
   open rc for
        select * from prerequisites;
        return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function show_classes 
return ref_cursor4 as
	rc ref_cursor4;

BEGIN
   open rc for
        select * from classes;
        return rc;


EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function show_enrollments 
return ref_cursor5 as

	rc ref_cursor5;
BEGIN
	
		open rc for
        select * from enrollments;
        return rc;

	
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function show_logs 
return ref_cursor6 as

	
	rc ref_cursor6;
BEGIN
	
	open rc for
        select * from logs;
        return rc;
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function prerequisite_courses(c_dept_code in prerequisites.dept_code%type , c_course# in prerequisites.course#%type)
return ref_cursor7 AS
	
	rc ref_cursor7;

BEGIN
	
	open rc for
	select pre_dept_code,pre_course# from prerequisites
	connect by prior   pre_dept_code = dept_code and  pre_course# = course#
	start with  dept_code = c_dept_code  and  course# =  c_course#;
	return rc;
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function displaystudents(c_sid IN enrollments.sid%type) 
return ref_cursor8 AS

	rc ref_cursor8;
	
BEGIN

	open rc for 
		select distinct e.sid , s.firstname , c.dept_code,c.course#,c.title
        from students s,classes cl,courses c , enrollments e
        where e.sid = c_sid and e.sid = s.sid and e.classid = cl.classid and cl.dept_code = c.dept_code and cl.course# = c.course#;
		return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

		
		
function nocourses(s_sid IN students.sid%type)
return nocourse AS
	rc nocourse; 
BEGIN

	open rc for
		select sid from students where sid = s_sid;
		return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;


function validcourses(s_classid IN classes.classid%type)
return v_course AS
	rc v_course; 
BEGIN

	open rc for
		select classid from classes where classid = s_classid;
		return rc;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;



		
procedure update_students(c_sid IN students.sid%type,c_firstname IN students.firstname%type ,c_lastname IN students.lastname%type,c_status IN students.status%type,
c_gpa IN students.gpa%type ,c_email IN students.email%type) IS 
     
BEGIN
    INSERT INTO students
   ( sid,firstname,lastname,status,gpa,email )
   VALUES
   ( c_sid,c_firstname,c_lastname,c_status,c_gpa,c_email  );


EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

function prerequisite_courses(c_dept_code in prerequisites.dept_code%type , c_course# in prerequisites.course#%type)
return ref_cursor9 AS
	
	rc ref_cursor9;

BEGIN
	
	open rc for
	select pre_dept_code,pre_course# from prerequisites
	connect by prior   pre_dept_code = dept_code and  pre_course# = course#
	start with  dept_code = c_dept_code  and  course# =  c_course#;
	return rc;
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;



function classstudents(c_cid IN enrollments.classid%type) 
return ref_cursor10 AS
	
	rc ref_cursor10;

BEGIN
	
	open rc for
	select s.sid , cl.classid , s.firstname ,c.title
	from students s , enrollments e , classes cl , courses c
	where  e.classid = c_cid and e.sid = s.sid and e.classid = cl.classid and cl.dept_code=c.dept_code and c.course# = cl.course#;
		return rc;
		
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);

end;

PROCEDURE enroll_student(s_sid IN students.sid%type,c_cid  IN classes.classid%type) is
	count1 NUMBER;
	count2 NUMBER;
	count3 NUMBER;
	count4 NUMBER;
	count5 NUMBER;
	class_size classes.class_size%type;
	class_limit classes.limit%type;
	invalid_class EXCEPTION;
	invalid_student EXCEPTION;
	exceeded_class_limit EXCEPTION;
	already_enrolled EXCEPTION;
	max_enrollment EXCEPTION;
	required_prerequisites EXCEPTION;
	overloading EXCEPTION;
	
BEGIN
	SELECT count(*) INTO count1 FROM enrollments e WHERE e.classid= c_cid;
	IF (count1 =0) THEN
		RAISE invalid_class;
	END IF;
	
	SELECT count(*) INTO count2 FROM students s WHERE s.sid= s_sid;
	IF (count2 = 0)THEN
		RAISE invalid_student;
	END IF;
	
	SELECT class_size,limit INTO class_size,class_limit FROM classes WHERE classid=c_cid;
    IF(class_size=class_limit) THEN
		RAISE exceeded_class_limit;
    END IF;

	SELECT count(*) INTO count3 FROM students s,enrollments e WHERE e.sid=s.sid and s.sid=s_sid and e.classid=c_cid;
	IF (count3 > 0) THEN
	RAISE already_enrolled;
	END IF;
	
	SELECT count(*) INTO count4
	FROM ((SELECT a.classid FROM classes a,classes b 
	WHERE a.semester=b.semester and a.year=b.year and b.year=(SELECT year FROM classes 
	WHERE classid=c_cid and b.semester=(SELECT semester FROM classes
	WHERE classid=c_cid)))
    INTERSECT
    (SELECT classid FROM enrollments WHERE sid=s_sid));
	
--		INSERT INTO enrollments values(s_sid,c_cid,null);
        
	IF (count4 > 3) THEN
	RAISE max_enrollment;
		raise_application_error(-20001,'The Student' || s_sid || 'is already enrolled FOR four classes'||SQLCODE||' -ERROR- '||SQLERRM );
	END IF;

	SELECT count(*) INTO count5 FROM((SELECT a.pre_dept_code,a.pre_course# FROM prerequisites a,classes b
	WHERE b.dept_code=a.dept_code and b.course#=a.course# and b.classid=c_cid)
	MINUS
	(SELECT c.dept_code,c.course# FROM classes c,enrollments e WHERE e.sid=s_sid and e.classid=c.classid and e.lgrade is not null));
	IF (count5 > 0)THEN 
	RAISE required_prerequisites;
	END IF;
		
		INSERT INTO enrollments values(s_sid,c_cid,null);
	IF (count4 = 3) THEN
		raise overloading;
	END IF;
EXCEPTION
	WHEN invalid_class THEN
		raise_application_error(-20001,'The class' || c_cid || 'is not valid');
	WHEN invalid_student THEN
		raise_application_error(-20001,'The student' || s_sid || 'is invalid');
	WHEN exceeded_class_limit THEN
		raise_application_error(-20001,'The class' || c_cid || 'is closed FOR enrolments');
	WHEN already_enrolled THEN
		raise_application_error(-20001,'The Student' || s_sid || 'is already enrolled FOR class' );
	WHEN max_enrollment THEN 
		raise_application_error(-20001,'The Student' || s_sid || 'is already enrolled FOR four classes');
	WHEN required_prerequisites THEN
		raise_application_error(-20001,'The Student' || s_sid || 'have not completed the required prerequisite courses');
	WHEN overloading THEN
		raise_application_error(-20001,'You are Overloading');
		
END enroll_student;

PROCEDURE drop_student(s_sid IN students.sid%type,c_cid  IN classes.classid%type)is
	count1 NUMBER;
	count2 NUMBER;
	count3 NUMBER;
	count4 NUMBER;
	count5 NUMBER;
	count6 NUMBER;
	temp1 NUMBER;
	temp2 NUMBER;
	invalid_class EXCEPTION;
	invalid_student EXCEPTION;
	not_enroll EXCEPTION;
	course_completion EXCEPTION;
	no_class_for_student EXCEPTION;
	no_students_in_class EXCEPTION;
	no_students_no_class EXCEPTION;

BEGIN
	SELECT count(*) INTO count1 FROM enrollments e WHERE e.classid= c_cid;
	IF (count1 =0) THEN
	RAISE invalid_class;
	END IF;
	
	SELECT count(*) INTO count2 FROM students s WHERE s.sid= s_sid;
	IF (count2 = 0)THEN
	RAISE invalid_student;
	END IF;
	
	SELECT count(*) INTO count3 FROM students s,enrollments e WHERE e.sid=s.sid and s.sid=s_sid and e.classid=c_cid;
	IF(count3 = 0) THEN 
	RAISE not_enroll;
	END IF;
	
	SELECT count(*) INTO count4 FROM students s,enrollments e 
	WHERE e.sid=s.sid and s.sid=s_sid and e.classid=c_cid and e.lgrade is not null;
	IF(count4 = 1) THEN
	RAISE course_completion;
	END IF;
	
	delete FROM enrollments WHERE sid=s_sid and classid=c_cid;	
	SELECT count(*) INTO count5 FROM enrollments WHERE sid=s_sid;
	IF(count5 = 0) THEN
		 temp1:=1;
    END IF;

    SELECT count(*) INTO count6 FROM enrollments WHERE classid=c_cid;
    IF(count6 = 0) THEN
        temp2:=1;
    END IF;

    IF(temp1=1 and temp2=0) THEN
		raise no_class_for_student;
    END IF;
	IF(temp2=1 and temp1=0) THEN
		raise no_students_in_class;
    END IF;

    IF(temp1=1 and temp2=1) THEN
		raise no_students_no_class;
	END IF;
	
EXCEPTION
	WHEN invalid_class THEN
		raise_application_error(-20001,'The class' || c_cid || 'is not valid');
	WHEN invalid_student THEN
		raise_application_error(-20001,'The student' || s_sid || 'is invalid');
	WHEN not_enroll THEN
		raise_application_error(-20001,'The student' || s_sid || 'is not enrolled IN the class' || c_cid);		
	WHEN course_completion THEN 
		raise_application_error(-20001,'The student' || s_sid || 'already completed the class' || c_cid);
	when no_class_for_student THEN
		raise_application_error(-20001,'The student is not enrolled IN any classes now');
	when no_students_in_class then
		raise_application_error(-20001,'The class has no students');
	when no_students_no_class then
		raise_application_error(-20001,'The class has no students and student now has enrolled IN any classes');
END drop_student;

PROCEDURE delete_student(s_sid IN students.sid%type) is
	 count1 NUMBER;
     invalid_student EXCEPTION;
BEGIN
    SELECT count(*) INTO count1 FROM students WHERE sid=s_sid;
    IF(count1 = 0) THEN
         RAISE invalid_student;
    END IF;

    delete FROM students WHERE sid=s_sid;

EXCEPTION
	WHEN invalid_student THEN
		raise_application_error(-20001,'The student' || s_sid || 'is invalid');
END delete_student;


end Project_MainPackage;
/
show errors;


