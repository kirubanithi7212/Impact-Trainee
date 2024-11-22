/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : October 13
 Updated at  : October 31
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class History
   {
	 // This method is used to see the history of puchase ,it shows when the purchase done and total amount
     public static void purchased()
       {
    	 int emptyCheck=0;String exceptionOccured="no"; 
    	 try 
              {
        	  	  Statement statement = Login.connection.createStatement();
        	  	  ResultSet resultsetForHistory = statement.executeQuery("SELECT * FROM history where userid='"+Login.userName+"'");
        	  	  System.out.printf("%-15s | %-7s | %-10s %n","userName","total","date");
        	  	  System.out.println("---------------------------------------------------------------------------");
        	  	  
        	  	  while(resultsetForHistory.next())
        	  	     {
        	  		   String User= resultsetForHistory.getString(1);
        	  		   int totalValue = resultsetForHistory.getInt(2);
        	  		   emptyCheck=totalValue;
        	  		   String dateHistory= resultsetForHistory.getString(3);
        	  		   System.out.printf("%-15s | %-7s | %-10s %n",User,totalValue,dateHistory);
        	  		   
        	  	     }
              } 
          catch (Exception exception) 
              {
        	      System.out.println(exception);
        	      System.out.println("something went wrong");
        	      exceptionOccured="yes";
              }
         if(emptyCheck==0 || exceptionOccured.equals("yes"))
        	  	     {
        	  		  System.out.println("There is no purchases");
        	  		  Menu.options();
        	  	     }
         else
         	{
        	 Scanner history=new Scanner(System.in);
        	 System.out.println("press 1 for main menu");
        	 int ChoiceForhistoryClass=history.nextInt(); 
        	 if(ChoiceForhistoryClass==1)
        	 {
        		 Menu.options();
        	 }
        	 else
        	   {
        		 System.out.println("enter valid choice");
        		 purchased();
        	   }
        	 }
       }
   }
