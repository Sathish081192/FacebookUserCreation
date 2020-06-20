package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBValidation {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DBValidation db=new DBValidation();

	}
	
	public void createdb() throws ClassNotFoundException, SQLException  {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String connectionurl="jdbc:oracle:thin:@localhost:1521:orcl";
		String username="system";
		String password="12345";
		String query="SELECT * FROM Customers where city='London'";
		
		Connection con=DriverManager.getConnection(connectionurl, username, password);
		
		Statement st=con.createStatement();
		
		ResultSet result = st.executeQuery(query);
		
		while(result.next()) {
			String customerID=result.getString("CustomerID");
			System.out.println(customerID);
		}
		con.close();
		
		
		
		
		
		
		/*Connection con=DriverManager.getConnection(connectionurl, username, password);
		
		Statement st=con.createStatement();
		
		
		ResultSet rs=st.executeQuery(query);
		
		
		while(rs.next()) {
			String ContactName=rs.getString("ContactName");
			System.out.println(ContactName);
		}
		con.close();*/
						
	}

}
