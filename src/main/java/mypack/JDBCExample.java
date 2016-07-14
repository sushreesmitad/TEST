
package mypack;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class JDBCExample {
	
	static {
		
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String saveStudent(Student s){
		String name = null;
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		Connection connection = null;
		try {

			
          System.out.println("in save student");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/testdb", "testuser","");
			Statement st = connection.createStatement();
			String nm = s.getName();
			int age = s.getAge();
			int roll = s.getRoll();
			String query =  "insert into Student (name,age,roll) values ('"+nm+"',"+age+","+roll+")";
			System.out.println("query is "+query);
			int record = st.executeUpdate(query);
			if(record > 0)
				System.out.println("Record inserted");

		} catch (SQLException e) {

			System.out.println("Exception occured while storing student details");
			e.printStackTrace();
			return null;

		}
		finally{
			System.out.println("in finally");
			try{
				
				connection.close();
			}
			catch(Exception e){}
		}

		
		
		return name;
	}
	
	public static String  getStudent(int roll){
		
		String name = null;
	try {
		System.out.println("in getStudent");
			
           	Connection connection = null;
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/testdb", "testuser","");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from Student where roll="+roll);
			if(rs.next()){

			name = rs.getString("name");
			System.out.println("name of student is"+name);


			}		
		

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}

		
		
		return name;
	}
		
	public static void displayAll(){
		
	try {

			
           	Connection connection = null;
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/testdb", "testuser","");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from Student");
			while(rs.next()){

			String name = rs.getString("name");
			String roll = rs.getString("roll");
			String age =  rs.getString("age");
			
			System.out.println("Name"+name+"roll"+roll+"Age"+age);

			}		
		

		} catch (SQLException e) {

			System.out.println("Exception....");
			e.printStackTrace();
		

		}

		
		

	}
		
	}	
	
	
	
		
	