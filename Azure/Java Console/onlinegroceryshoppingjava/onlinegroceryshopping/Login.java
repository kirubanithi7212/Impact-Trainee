/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : october 13
 Updated at  : october 20
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.*;
import java.util.Scanner;
public class Login 
	 {
	    
	    static Connection connection ;
	    static String userName;
	    static void logIn()
	      {
	    	String userNameFromUser,passwordFromUser;
	    	String passwordInDb="password",exceptionOccurs="no";
		    connection=JdbcConnection.getConnection();
		    Scanner inputforlogin= new Scanner(System.in);	
		    System.out.print("\n Enter your user id :");
		    userNameFromUser= inputforlogin.nextLine();
		    System.out.print("\n Enter your password :");
		    passwordFromUser = inputforlogin.nextLine();
		    	  try 
		    	    {
		    		    Statement statement=connection.createStatement();
		    		    ResultSet login= statement.executeQuery(" select password from userdetails where userid='"+userNameFromUser+"';");
		    		    while(login.next())
		    		       {
		                       passwordInDb=login.getString(1);
		                       if(passwordInDb!=passwordFromUser)
		                          {
		                    	   OnlineGroceryShopping.forgot+=1;
		                          }
		    		       }
		    	    }
		    	  catch(Exception userNotInDb)
		    	  	 	{
		    	  			System.out.println( "     USER NOT FOUND    ");
		    	  			exceptionOccurs="yes";
		    	  			OnlineGroceryShopping.productPurchase();
		    	  	 	}
		    	 if(exceptionOccurs!="yes")
		    	 {
		    	  if(passwordInDb.equals(passwordFromUser))
		    		       {
		    		    	  userName=userNameFromUser;
		    		    	  Menu.options();
		    		       }
		    	  else
		    		      {
		    		    	System.out.println("Wrong password");
		    		    	OnlineGroceryShopping.productPurchase();
		    		      }
		    	 }
		    	  
	      }
	 }

    
    
    

