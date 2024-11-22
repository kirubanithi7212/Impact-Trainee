/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : october 13
 Updated at  : october 20
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Product
    {
	    static boolean flagForLoop=true;
	    static Scanner input=new Scanner(System.in);
	    // This product method is used to show the products
	    public static void show() 
	    	{
	    	 String exception1="no";  
	    	 try
	    	   	 {
	    		     Statement statement=Login.connection.createStatement();
	    		     ResultSet products= statement.executeQuery("Select * from products");
	    		     System.out.printf("%-4s | %-20s | %-3s %n","no","names","price");
	    		     System.out.println("--------------------------------------------------------------");
	    		     while(products.next())
	    		        {
	    		    	 	int no=products.getInt(1);
	    		    	 	String names=products.getString(2);
	    		    	 	int price=products.getInt(3);
	    		    	 	System.out.printf("%-4s | %-20s | %-3s %n",no,names,price);
	    		        }
	    	   	 }
	    	   catch(SQLException exception)
	    	     {
	    		    System.out.println(exception);
	    		    System.out.println("Something Went Wrong");
	    		    exception1="yes";
	    		 }
	    	 if(exception1.equals("no"))
	    	 {
	    	         System.out.println("PRESS 1 to Add item to cart (OR) PRESS 2 FOR HOME PAGE\n");
	    		     String choiceForProduct=input.nextLine();
	    		     if(choiceForProduct.equals("1"))
	    		        {
	    		    	 System.out.println("if you want to stop adding type stop after this  _Stop?_");
		            	  while(flagForLoop==true)
		            	     {
		            		    Cart.adding();
		            	     }
		            	  System.out.println("       your products are added to cart      ");
		            	  flagForLoop=true;
		            	  Menu.options();
	    		        }
	    		     else if(choiceForProduct.equals("2"))
	    		        {
	    		    	  Menu.options();
	    		        }
	    		     else
	    		        {
	    		    	  System.out.println("     Enter valid choice     ");
		            	  Product.show();
	    		        }
	    	 }
	    	 else
	    	  {
	    		 Menu.options();
	    	  }
	    }
    }
