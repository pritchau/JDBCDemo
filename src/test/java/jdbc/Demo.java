package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

	public static void main(String[] args)  {
		
		String Url = "jdbc:mysql://localhost:3306/";
		String dbName = "demo";
		String dbURL = Url+dbName;
		String username = "root";
		String password = "root";
		
		Connection connection =null;
		
		try {
			
			//create an a object for mysql jdbc driver class
			
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver).newInstance();
			
			//connect to Demo database system
			
			 connection = DriverManager.getConnection(dbURL, username, password);
			
			if(!connection.isClosed()) {
				
				System.out.println("Successfully connected to Demo database");
				
				//Execute the query to retrive the employee table records
				
//				Statement statment = connection.createStatement();
//				
//				ResultSet resultSet = statment.executeQuery("select * from Employee");
//				
//				while(resultSet.next() ) {
//					System.out.println(resultSet.getString("Name")+"--"+resultSet.getString("Location")+"--"+resultSet.getInt("Experience"));
//					
//				}
				
				//Execute prepared statment to retrive the filtered records from employee records
				
				PreparedStatement prepareStament = connection.prepareStatement("select * from Employee where name = ? and Location = ?");
				prepareStament.setString(1, "Swati");
				prepareStament.setString(2, "Aurngabad");
				ResultSet resultSet2 = prepareStament.executeQuery();
				
				while(resultSet2.next()) {
					System.out.println(resultSet2.getString("Name")+"--"+resultSet2.getString("Location")+"--"+resultSet2.getInt("Experience"));
				}
			}
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			//Close database connection
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(connection.isClosed()) {
					System.out.println("Successfully Demo database close");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	
		
		
	}

}
