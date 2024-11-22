/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : October 13
 Updated at  : October 20
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.Connection;
import java.sql.DriverManager;
public class JdbcConnection 
    {
        static Connection connection;
        // this method is used to give connection
        public static Connection  getConnection()
        	{
        		try 
        		  {
        			 //Class.forName("com.mysql.jdbc.Driver");
        			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinegroceryshopping","root","kiruba@@7212");
        		  }
        		catch(Exception exception)
        		  {
        			 System.out.println(exception);
        		  }
        		return connection;
        	}
    }

