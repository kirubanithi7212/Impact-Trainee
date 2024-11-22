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
public class OrderProduct {
	// Scanner input=new Scanner(System.in);
	static int finalTotal=0;
	static String date=null;
	// This method is used to confirm the order
	static void confirm()
	  {
		int totalForOrder=0;String exception5="no";
		try {
			 Statement statement=Login.connection.createStatement();
		    ResultSet cartlist= statement.executeQuery("Select * from cart where userid='"+Login.userName+"';");
		   
		    
		    System.out.printf(" %-20s | %-10s | %-10s | %-10s | %n","name","itemprice","quantity","price");
		    System.out.println("------------------------------------------------------------------------------------");
		    while(cartlist.next())
		    {
		    	String productName=cartlist.getString(2);
		    	int indidualPrice=cartlist.getInt(3);
		    	int quantity=cartlist.getInt(4);
		    	int price=cartlist.getInt(5);
		    	totalForOrder=totalForOrder+price;
		    			    	
		    	System.out.printf(" %-20s | %-10s | %-10s | %-10s | %n",productName,indidualPrice,quantity,price);
		    	
		    }
		}
    	catch(Exception reference) {
    	    System.out.println(reference);
    		System.out.println("   there is no product    ");
    		exception5="yes";
    	}
		    if(totalForOrder!=0)
		    {
		    System.out.println("                                          total : "+totalForOrder);
		    System.out.println();
		    try
		    {
		    Statement statementForAlter=Login.connection.createStatement();
		    ResultSet userdetails=statementForAlter.executeQuery("select name,gender,address,phoneno,emailid from userdetails where userid='"+Login.userName+"';");
		    while(userdetails.next())
		    {
		    	String name =userdetails.getString(1);
		    	String gender=userdetails.getString(2);
		    	String address =userdetails.getString(3);
		    	String phoneno =userdetails.getString(4);
		    	String emailId =userdetails.getString(5);		    			    	
		    	System.out.println("***User Details***");
		    	System.out.println("Name : "+name);
		    	System.out.println("gender : "+gender);
		    	System.out.println("address : "+address);
		    	System.out.println("phone no  : "+phoneno);
		    	System.out.println("emailid : "+emailId);	
		    	System.out.println("The products are delivered  to the bellow address : \n "+" "+"Address : "+address);
		    }
		    }
		    catch(SQLException reference)
		    {
		    	System.out.println("somethig went wrong");
		    	exception5="yes";
		    	
		    }
		    }
		    if(totalForOrder==0 || exception5.equals("yes"))
		    {
		    	System.out.println("*no data please add from product (or) Something Went wrong Try again*");
		    	Menu.options();
		    }
		    else
		    {
		       finalTotal=totalForOrder;
		       System.out.print("press   1   for confirm order (or)   2   for Menu");
		       String choiceForOrdered=OnlineGroceryShopping.input.next();
		       if(choiceForOrdered.equals("1"))
		       	{
		    	   System.out.println("Note : The date is only in this format YYYY-MM-DD  \n Enter today date :  ");
		    	   date=OnlineGroceryShopping.input.next();
		    	   String queryForOrder=" insert into ordered(productname,priceofproduct,quantity,price,total,dateofpurchase,userid)select productname,priceofproduct,quantity,price,"+finalTotal+",'"+date+"',userid from cart ;";
		    	   PreparedStatement order;
		    	   try 
		    	   		{
		    		   		order = Login.connection.prepareStatement(queryForOrder);
		    		   		order.executeUpdate();
		    		   		PreparedStatement clearcart = Login.connection.prepareStatement("delete from cart where userid='"+Login.userName+"';");
		    		   		clearcart.executeUpdate();
		    		   		PreparedStatement insertintohistory  = Login.connection.prepareStatement("insert into history(userid,total,dateofpurchase)values('"+Login.userName+"',"+finalTotal+",'"+date+"');");
		    		   		insertintohistory.execute();
		    	   		}
		    	   catch (SQLException exception) 
		    	   		{
		    		   		System.out.println(exception);
		    		   		exception5="yes";
		    	   		}
		    	   if(exception5.equals("no"))
		    	   {
		    	   System.out.println("the items will be delivered soon...");
		    	   Menu.options();
		       		}
		    	   else
		    	     {
		    		   System.out.println("Something went wrong");
		    		   confirm();
		    	     }
		       	}
		       else if(choiceForOrdered.equals("2"))
		       	{
		    	   Menu.options();
		       	}
		       else
		       	{
		    	   System.out.println("enter valid option");
		    	   confirm();
		       	}
		    }	
	  }	
}

	  

	


