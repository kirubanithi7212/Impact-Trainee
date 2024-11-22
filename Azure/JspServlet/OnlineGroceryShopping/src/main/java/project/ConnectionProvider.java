package project;
import java.sql.*;
public class ConnectionProvider {
	public static Connection getCon()
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/webapplication","root","kiruba@@7212");
    		return connection;
    		
    	}
    	catch(Exception reference)
    	  {
    		System.out.println(reference);
    		return null;
    	  }
    }
}
