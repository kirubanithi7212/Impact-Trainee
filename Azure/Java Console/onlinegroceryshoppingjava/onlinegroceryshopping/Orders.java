/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : october 13
 Updated at  : october 20
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//ABSTRACTION IS USED HERE
abstract class CancelOrder
      {
	     abstract void cancel();
      }
public class Orders extends CancelOrder
     {
	     static Scanner input = new Scanner(System.in);
	     // This cancel method is used to cancel the order
	     void cancel()
           {
	    	 int total=0;String exceptionOccured="no";
	    	 try
    	        {
    	            Statement statement=Login.connection.createStatement();
	                ResultSet orderedList= statement.executeQuery("Select productname,priceofproduct,quantity,price,total,dateofpurchase from ordered where userid='"+Login.userName+"';");
                    System.out.printf("%-25s | %-20s | %-4s | %-4s | %-10s  %n","name","priceOfProduct","quantity","price","dateOfPurchase");
                    System.out.println("------------------------------------------------------------------------------------");
                    
                    while(orderedList.next())
                       {
    	                   String names=orderedList.getString(1);
    	                   int indidualPrice=orderedList.getInt(2);
    	                   int quantity=orderedList.getInt(3);
    	                   int price=orderedList.getInt(4);
    	                   total=orderedList.getInt(5);
    	                   String date=orderedList.getString(6);
    	                   System.out.printf("%-25s  | %-20s | %-4s | %-4s | %-10s  %n",names,indidualPrice,quantity,price,date);
		               }
    	        }
    	     catch(Exception exception)
    	        {
    	    	  exception.printStackTrace();
    	    	  System.out.println("order not deleted");
    	    	  exceptionOccured="yes";
    	        }
    
                    if(total==0 ||exceptionOccured.equals("yes"))
                       {
    	                   System.out.println("  There is no order (OR) semething went wrong ");
    	                   Menu.options();
                       }
                    else
                    {
                    	System.out.println("\n press   1  for delete all your orders   (or) press 2 for cancel single  order (or) press   3   for mainmenu");
        	            String cancelChoice=input.next();
                        if(cancelChoice.equals("1"))
                           {
          	                        try
          	                          {  
          	                        	PreparedStatement cancelAll= Login.connection.prepareStatement("delete from ordered where userid='"+Login.userName+"';");
          	                        	cancelAll.execute();
          	                        	PreparedStatement clearHistory= Login.connection.prepareStatement("delete from history where userid='"+Login.userName+"';");
          	                        	clearHistory.execute();
          	                        	
          	                          }
          	                        catch(SQLException referenceVariable)
          	                          {
          	                        	System.out.println("Something went wrong");
          	                        	exceptionOccured="yes";
          	                          }
          	                      if(exceptionOccured.equals("no"))
          	                        {
          	                    	  System.out.println("    your order is cancelled sucessfully     ");
          	                    	  Menu.options();
          	                        }
          	                      else
          	                        {
          	                    	  System.out.println("Something Went wrong");
          	                    	  Menu.options();
          	                        }
                           }
                        else if(cancelChoice.equals("2"))
          	                      {
          		                    String dateOfPurchase;
          		                    System.out.println("Enter the date of order  \n Note : The date is only in this format YYYY-MM-DD ");
          		                    dateOfPurchase =input.next();
          		                    System.out.println();
          		                    try
          		                      {
          		                    	PreparedStatement cancelParticularOrder= Login.connection.prepareStatement("delete from ordered where userid='"+Login.userName+"' and dateofpurchase='"+dateOfPurchase+"';");
          		                          cancelParticularOrder.execute();
          		                         PreparedStatement deleteFromHistory= Login.connection.prepareStatement("delete from history where  userid='"+Login.userName+"' and dateofpurchase='"+dateOfPurchase+"';");
          		                         deleteFromHistory.execute();
          		                      }
          		                    catch(SQLException referenceVar)
          		                      {
          		                    	System.out.println("Somrthing Went Wrong");
          		                    	exceptionOccured="yes";
          		                      }
          		                    if(exceptionOccured.equals("no"))
          		                    {
          		                    System.out.println("    your order is cancelled sucessfully     ");
          		                    Menu.options();
          		                    }
          		                    else
          		                     {
          		                    	System.out.println("Somethindg Went Wrong \n please try again");
          		                    	cancel();
          		                     }
          	                      }
                        else if(cancelChoice.equals("3"))
                          	      {
                          	    	  Menu.options();
                          	    	 
                          	      }
                        else
                          	      {
                          	    	  System.out.println("   Enter valid option   ");
                          	    	  cancel();
                          	      }
                    }
                       
    	        
           }
     }    

