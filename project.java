import java.sql.*; 
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import java.lang.*;
import oracle.jdbc.pool.OracleDataSource;

public class project 
{
	//public static Connection conn = null;
	
   public static void main (String args []) throws SQLException {
    try
    {
		char s,s1;

        //Connection to Oracle server
        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1","su455494");
		System.out.println("\n/**************************Connection has been established with the server\n");
		System.out.println("/**************************WELCOME TO STUDENTS REGISTRATION FORM**************************/\n");
		conn.close();	
	do
	{
		System.out.println("\n1.Display All The Tables");
		System.out.println("2.Courses taken by a particular Student (Enter SID)");
		System.out.println("3.Add Student");
		System.out.println("4.Remove Student");
		System.out.println("5.Enroll a Student in a Particular Course");
		System.out.println("6.Remove a Student from a Particular Course");
		System.out.println("7.Display Prerequisites of a Particular Course");
		System.out.println("8.Display Students of a Particular classid");
		System.out.println("9.Exit");
		
		System.out.println("\n/******************************PLease Enter your Option****************************/\n");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        	s = (char)bufferRead.read();
		
		switch(s)
		{
			
			case '1': 
					do
					{
						System.out.println("\n/******************************PLease Enter your Option****************************/\n");
						System.out.println("------------------> 1.Display students data");
						System.out.println("------------------> 2.Display classes data");
						System.out.println("------------------> 3.Display prerequisite data");
						System.out.println("------------------> 4.Display enrollments data");
						System.out.println("------------------> 5.Display courses data");
						System.out.println("------------------> 6.Display logs data");
						System.out.println("------------------> 7.Go to Previous Menu\n");
					
						BufferedReader bufferRead1 = new BufferedReader(new InputStreamReader(System.in));
								s1 = (char)bufferRead1.read();

						switch(s1)
						{

							case '1':	System.out.println("/*********************Students Data*********************/");
										students();
									    break;
							case '2':	System.out.println("\n/*********************Classes Data*********************\n");
										classes();
                                        break;
							case '3':	System.out.println("\n/*********************Prerequisite Data*********************\n");
										prerequisites();
                                        break;
							case '4':	System.out.println("\n/*********************Enrollments Data*********************\n");
										enrollments();
                                        break;
							case '5':	System.out.println("\n/*********************Courses Data*********************\n");
										courses();
                                        break;
							case '6':	System.out.println("\n/*********************Logs Data*********************\n");
										logs();
                                        break;
							case '7':	break;
							
							default: System.out.println("Wrong input Selection!!!");
										break;
							
						}
					}while (s1 != '7');	
						break;
					
			case '2':  		SearchSid();
                            break;
            case '3':       AddStudent();
                                	break;
            case '4':       DeleteStu();
                                	break;
            case '5':       EnrollStudent();
                                	break;
            case '6':       RemoveStudent();
                                	break;
            case '7':       DisplayPrereq();
									break;
			case '8':		ClassidStu();
								break;
			case '9':	System.out.println("/***********************************Thankyou And Have a Great Day!!!***********************************/");
					break;
			default : System.out.println("Wrong Input Selected!!!");
						break;
				
		}
	}while(s != '9');
	
    }
	
	catch (SQLException ex) 
	{
		System.out.println("SQL Exception caught\n" +  ex.getMessage());
	}
	catch (Exception e) 
	{
		System.out.println("" + e.getMessage());
	}
 }
 
 
 public static void DeleteStu()
 {
	String s3 =null;
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		
		System.out.println("Enter SID of the Student and Should start with B");
		BufferedReader bufferRead3 = new BufferedReader(new InputStreamReader(System.in));
		s3 = bufferRead3.readLine();
		
		
		//Prepare to call stored procedure:
		
//        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.update_students(?,?,?,?,?,?); end;");
		        CallableStatement cs = conn.prepareCall("begin Project_MainPackage.delete_student(?); end;");
        
        
		cs.setString(1,s3);
		
        
        // execute and retrieve the result set
        cs.executeQuery();
		/*
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6));
        }
		*/
		//System.out.println("Done!!!");
        //close the result set, statement, and the connection
		System.out.println("Successfully deleted!!!");
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

 
 
 
 public static void RemoveStudent()
 {
	String s3 =null,s4=null;
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		
		System.out.println("Enter SID of the Student and Should start with B");
		BufferedReader bufferRead3 = new BufferedReader(new InputStreamReader(System.in));
		s3 = bufferRead3.readLine();
		
		System.out.println("Enter the ClassID:");
		BufferedReader bufferRead4 = new BufferedReader(new InputStreamReader(System.in));
		s4 = bufferRead4.readLine();
		
		//Prepare to call stored procedure:
		
//        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.update_students(?,?,?,?,?,?); end;");
		        CallableStatement cs = conn.prepareCall("begin Project_MainPackage.drop_student(?,?); end;");
        //register the out parameter (the first parameter)
        //cs.registerOutParameter(1, OracleTypes.CURSOR);
        
		cs.setString(1,s3);
		
		cs.setString(2,s4);
		
        
        // execute and retrieve the result set
        cs.executeQuery();
		/*
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6));
        }
		*/
		//System.out.println("Done!!!");
        //close the result set, statement, and the connection
		System.out.println("Successfully Removed!!");
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

 
 
 
 
 
 public static void EnrollStudent()
 {
	String s3 =null,s4=null;
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		
		System.out.println("Enter SID of the Student and Should start with B");
		BufferedReader bufferRead3 = new BufferedReader(new InputStreamReader(System.in));
		s3 = bufferRead3.readLine();
		
		System.out.println("Enter the ClassID:");
		BufferedReader bufferRead4 = new BufferedReader(new InputStreamReader(System.in));
		s4 = bufferRead4.readLine();
		
		//Prepare to call stored procedure:
		
//        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.update_students(?,?,?,?,?,?); end;");
		        CallableStatement cs = conn.prepareCall("begin Project_MainPackage.enroll_student(?,?); end;");
        //register the out parameter (the first parameter)
        //cs.registerOutParameter(1, OracleTypes.CURSOR);
        
		cs.setString(1,s3);
		
		cs.setString(2,s4);
		
        
        // execute and retrieve the result set
        cs.executeQuery();
		/*
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6));
        }
		*/
		//System.out.println("Done!!!");
        //close the result set, statement, and the connection
		System.out.println("Successfully Enrolled");
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

 
 
 
 
 
 
 
 public static void ClassidStu()
 {
	String s2 = null;
	int flag=0;
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		
		System.out.println("Enter the ClassID:");
		BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
		s2 = bufferRead2.readLine();
		
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.classstudents(:2); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
		cs.setString(2,s2);
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
			flag =10;
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + "\t"+
                rs.getString(4) );
        }
		CallableStatement cs1 = conn.prepareCall("begin ? := Project_MainPackage.validcourses(:2); end;");
        //register the out parameter (the first parameter)
        cs1.registerOutParameter(1, OracleTypes.CURSOR);
        cs1.setString(2,s2);
        
        // execute and retrieve the result set
        cs1.execute();
		
        ResultSet rs1 = (ResultSet)cs1.getObject(1);
		 
		 while(rs1.next())
		 {
			flag++;
		 
		 }
		if(flag==1)
				System.out.println("No Student has enrolled in this class!!!");
		
		if(flag == 0)
		        System.out.println("Wrong ClassID is Given!!!");

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
 
 
 public static void DisplayPrereq()
 {
	String s2 = null;
	int s1;
	int flag = 0;
	try{
		
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		System.out.println("Enter the Department Code:");
		BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
		s2 = bufferRead2.readLine();
		
		
		System.out.println("Enter the Course Number:");
		BufferedReader bufferRead1 = new BufferedReader(new InputStreamReader(System.in));
		s1 = Integer.parseInt(bufferRead1.readLine());
		
		
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.prerequisite_courses(?,?); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.setString(2,s2);
        cs.setInt(3,s1);
        // execute and retrieve the result set
        cs.execute();
		
        ResultSet rs = (ResultSet)cs.getObject(1);
		
        // print the results
        while (rs.next()) {
			flag = 1;
            System.out.println(rs.getString(1) +""+
                rs.getString(2));
        }
		if(flag==0)
				System.out.println("No PreRequistes is required for this course!!!");
        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
 
 

 public static void AddStudent()
 {
	String s3 =null,s4=null,s5=null,s6=null,s8=null;
	int s7;
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		
		System.out.println("Enter SID of the Student and Should start with B");
		BufferedReader bufferRead3 = new BufferedReader(new InputStreamReader(System.in));
		s3 = bufferRead3.readLine();
		
		System.out.println("Enter First Name of the Student:");
		BufferedReader bufferRead4 = new BufferedReader(new InputStreamReader(System.in));
		s4 = bufferRead4.readLine();
		
		System.out.println("Enter Last Name of the Student:");
		BufferedReader bufferRead5 = new BufferedReader(new InputStreamReader(System.in));
		s5 = bufferRead5.readLine();
		
		
		System.out.println("Enter Status of the Student:");
		BufferedReader bufferRead6 = new BufferedReader(new InputStreamReader(System.in));
		s6 = bufferRead6.readLine();
		
		System.out.println("Enter GPA of the Student:");
		BufferedReader bufferRead7 = new BufferedReader(new InputStreamReader(System.in));
		s7 = Integer.parseInt(bufferRead7.readLine());
		
		
		System.out.println("Enter Email_ID of the Student:");
		BufferedReader bufferRead8 = new BufferedReader(new InputStreamReader(System.in));
		s8 = bufferRead8.readLine();
		
		//Prepare to call stored procedure:
		
//        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.update_students(?,?,?,?,?,?); end;");
		        CallableStatement cs = conn.prepareCall("begin Project_MainPackage.update_students(?,?,?,?,?,?); end;");
        //register the out parameter (the first parameter)
        //cs.registerOutParameter(1, OracleTypes.CURSOR);
        
		cs.setString(1,s3);
		
		cs.setString(2,s4);
		
		cs.setString(3,s5);
		
		cs.setString(4,s6);
		
		cs.setInt(5,s7);
		
		cs.setString(6,s8);
		
        
        // execute and retrieve the result set
        cs.executeQuery();
		/*
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6));
        }
		*/
		System.out.println("Record Successfully Inserted!!!");
        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex)
	{
		System.out.println("Record not Inserted Properly!!!");
		String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);
	}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }





 
 public static void SearchSid()
 {
	int flag =0;
	String s2 = null;
	try{
		
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		System.out.println("Enter The Sid Number of a particular Student:");
		BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
		s2 = bufferRead2.readLine();
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.displaystudents(?); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.setString(2,s2);
        
        // execute and retrieve the result set
        cs.execute();
		
        ResultSet rs = (ResultSet)cs.getObject(1);
		
        // print the results
        while (rs.next()) {
			flag =10;
			//System.out.println(flag);
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4));
        }
		
		CallableStatement cs1 = conn.prepareCall("begin ? := Project_MainPackage.nocourses(?); end;");
        //register the out parameter (the first parameter)
        cs1.registerOutParameter(1, OracleTypes.CURSOR);
        cs1.setString(2,s2);
        
        // execute and retrieve the result set
        cs1.execute();
		
        ResultSet rs1 = (ResultSet)cs1.getObject(1);
		 
		 while(rs1.next())
		 {
			flag++;
		 
		 }
		if(flag==1)
				System.out.println("The student has not taken any course!!!");
		
		if(flag == 0)
		        System.out.println("Wrong SID is Given!!!");

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) 
	{
		String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);
	}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
 
 
 
 
 
 public static void students()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_students(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
  public static void classes()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_classes(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + "\t"+
                rs.getString(4) + 
                "\t" + rs.getDouble(5) + "\t" +
                rs.getString(6) +"\t" +rs.getString(7) +" \t" + rs.getString(8));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
  public static void courses()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_courses(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
  public static void prerequisites()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_prerequisites(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
  public static void enrollments()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_enrollments(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
  public static void logs()
 {
 
	try{
	
		OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@grouchoIII.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("hyerram1", "su455494");
		
		//Prepare to call stored procedure:
		
		System.out.println("1");
		
        CallableStatement cs = conn.prepareCall("begin ? := Project_MainPackage.show_logs(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
		System.out.println("2");
        
        // execute and retrieve the result set
        cs.executeQuery();
        ResultSet rs = (ResultSet)cs.getObject(1);
		
		System.out.println("3");

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + rs.getString(3) + 
                rs.getString(4) + 
                "\t" + rs.getString(5) + "\t" +
                rs.getString(6));
        }
		System.out.println("4");

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
	}
	catch (SQLException ex) { String str = ex.getMessage();
		String[] temp;
		temp = str.split("ORA");
		
		System.out.println ("\n*** SQLException caught ***\n" + temp[1]);}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
}
