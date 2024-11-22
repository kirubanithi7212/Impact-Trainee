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
   //  HERE INHERITANCE IS USED( TO ACCESS THE  VARIABLE OF ANOTHER VLASS)
public  class Cart extends Product 
     {
    	static Scanner input=new Scanner(System.in);
        // This show method is used to show the items in cart
    	public static void selected()
	      {
    		String exceptionOccured="no";
    		int emptyCart=0; 
    		try {
            	   	Statement statement=Login.connection.createStatement();
        	        ResultSet cartList= statement.executeQuery("Select * from cart where userid='"+Login.userName+"';");
		   		    System.out.printf(" %-20s | %-10s | %-10s | %-10s | %n","name","itemPrice","quantity","price");
		   		    System.out.println("------------------------------------------------------------------------------------");
		   		    while(cartList.next())
		   		       {
		   		    	   String productName=cartList.getString(2);
		   		    	   int indidualPrice=cartList.getInt(3);
		   		    	   int quantity=cartList.getInt(4);
		   		    	   int price=cartList.getInt(5);
		   		    	   emptyCart=price;
		   		    	   System.out.printf(" %-20s | %-10s | %-10s | %-10s | %n",productName,indidualPrice,quantity,price);
		   		       }
		   		  }
             catch(Exception exception) 
                {
        		   System.out.println(exception);
        		   exceptionOccured="yes";
                }
            if(exceptionOccured.equals("no"))
            {
    		if(emptyCart==0)
 		       {
 		    	   System.out.println(" No data found . please add from product ");
 		    	   Menu.options();
 		       }
    		else
    		 {
             System.out.print("PRESS 1 for place order \n PRESS 2 to Alter the quantity of the item \n press 3 for delete the item \n press 4 forMainmenu \n");
             String choiceForCart=input.next();
             if(choiceForCart.equals("1"))
                {
                	OrderProduct.confirm();
                	Menu.options();
                }
             else if(choiceForCart.equals("2"))
                {
            	 System.out.println("Enter the product name which you want change the quantity :");
		    	   String itemName=OnlineGroceryShopping.input.next();
	    		   System.out.println("Enter the quantity :");
	    		   int quantity=OnlineGroceryShopping.input.nextInt();
	    		   Cart.alterProduct(itemName, quantity);
	    		   selected();
                }
       	    else if(choiceForCart.equals("3"))
    	    	 {
    	    		System.out.println("Enter the product name :");
    	    		String itemName=OnlineGroceryShopping.input.next();
    	    		Cart.alterProduct(itemName);
    	    		selected();
    	    		
    	         }
       	    else if(choiceForCart.equals("4"))
       	    	    {
       	    		 Menu.options();
       	    	    }
       	     else
       	    	{
       	    	   System.out.print("Enter valid option");	 
       	    	   selected();
       	    	}
    		}	     
            }
            else
            {
            	Menu.options();
            }
	      }
    	//this method is used to add items to the cart 
    	public static void adding()
          {  
    		 int insertPrice=0;int priceOfProduct=0;
         	 System.out.println("enter the item no :");
         	 int productId=input.nextInt();
         	 System.out.println("enter quantity want :(ONLY NUMBERS ARE ALLOWDED)");
         	 int quantityGiven=input.nextInt();
         	 try
         	    {
         		   Statement statementForPrice=Login.connection.createStatement();
         		   ResultSet price= statementForPrice.executeQuery("Select  priceofproduct from products where productid="+productId+";");
         		   while(price.next())
 			         {
         			    priceOfProduct=price.getInt(1);
 				        insertPrice=priceOfProduct*quantityGiven;
 			         }
         		   if(priceOfProduct==0)
         		   {
         			   System.out.println("invalid productid");
         			   show();
         		   }
         		   else
         		   {
         		   String adding=" insert into cart(userid,productname,priceofproduct,quantity,price)select '"+Login.userName+"',productname,priceofproduct,"+quantityGiven+","+insertPrice+" from products where productid="+productId+" ;";
         		   PreparedStatement cart= Login.connection.prepareStatement(adding);	
         		   cart.execute(); 
         		  System.out.println();
              	 System.out.println("Type  STOP for end the process (or) press any number for continue the process");
              	 String stopProcess=input.next();
              	 if(stopProcess.equals("stop"))
              	 	{
              		 	flagForLoop=false;
              	 	}
         		   }
         	    }
         	 
         	 
         	catch(Exception exception)
     	    {
     		   System.out.println(exception);
     		   System.out.println("Products are not added ");
     		   flagForLoop=false;
     	    }
          }
	     
    	// HERE POLYMORPHISM IS USED  (This method is used to change the quantity of the item)   	
    	public static void alterProduct(String itemName,int quantity)
	       {
	    	  int insertPrice=0;
	    	  try 
	    	  	{
	    		   Statement statementForAlterQuantity=Login.connection.createStatement();
	    		   ResultSet price= statementForAlterQuantity.executeQuery("Select  priceofproduct from cart where productname='"+itemName+"';");
	    		   while(price.next())
	    		      {
	    			      int PriceOfProduct=price.getInt(1);
	    			      insertPrice=PriceOfProduct*quantity;
	    		      }
	    	  	}
	    	  catch(Exception exceptionForQuantity)
	    	    {
	    		  System.out.println(exceptionForQuantity); 
	    		  System.out.println("*input is miss matched (or) not there , please enter correct spelling in the above table : *");
			   	  
	    	    }
	    	  try 
	    	    {    
		   		   String update="update cart set quantity="+quantity+", price="+insertPrice+" where productname='"+itemName+"';";
				   PreparedStatement cart=Login.connection.prepareStatement(update);	
			       cart.executeUpdate();
				} 
	    	  catch (SQLException exceptionForQuantity2) 
			    {
	    		  System.out.println(exceptionForQuantity2);
				   System.out.println(" input is miss matched , please enter correct spelling in the above table :  ");
			    }
	    	  if(insertPrice==0)
	    	    {
	    		  System.out.println("   There is no products in this name   ");
	    	    }
	    	  else
	    	    {
	    		  System.out.println("   Successfully quantity changed   ");
	    	    }
	       }
        // This is used to remove the product from cart
    	public static void alterProduct(String itemName)
	       {
	    	  int quantity=0;
    		  try 
	    	    {
    			  Statement statementForAlter=Login.connection.createStatement();
    			  ResultSet resultSet=statementForAlter.executeQuery("select quantity from cart where productname='"+itemName+"' and userid='"+Login.userName+"';"); 
				  while(resultSet.next())
				  {
					 quantity= resultSet.getInt(1);
				  }
	    		  String queryforupdate="delete from cart where productname='"+itemName+"' and userid='"+Login.userName+"';";  
				   PreparedStatement cart;
				   cart = Login.connection.prepareStatement(queryforupdate);
				   cart.executeUpdate();
			    } 
	    	  catch (SQLException referenceForAlter) 
	    	  	{
	    		  System.out.println(referenceForAlter);
	    		   System.out.print("Input is miss matched , please enter correct spelling in the above table :  ");
	    	  	}
    		  if(quantity==0)
    		  {
    			  System.out.println("there is no product");
    		  }
    		  else
    		  {
    			  System.out.println("   successfully deleted  ");
    		  }
	       }
     }   	
         
         
    
             
	     
    
         

