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
class Menu 
    {
	    // This page of method is used to show the menu menu
	 	static void options()
		    {
	    	    Scanner input=new Scanner(System.in);
		    	System.out.println("\n\n  welcome to KMS shopping**");
				System.out.println("1.products");
				System.out.println("2.cart");
				System.out.println("3.place order");
				System.out.println("4.Your orders");
				System.out.println("5.History");
				System.out.println("6.Help");
				System.out.println("7.Logout");
				System.out.println("--------------------Enter your option :---------------------");
				int choiceForMain=input.nextInt();
				try
		    	   {
				      switch(choiceForMain)
		    	          {
		    	          	 case 1:
		    	          	    {
		    	          	    	Product.show();
		    	          	    	break;
		    	          	    }
		    	          	 case 2:
		    	          	    {
		    	          	    	Cart.selected();
		    	          	    	break;
		    	          	    }
		    	          	 case 3:
		    	          	 	{
		    	          	 		OrderProduct.confirm();
		    	          	 		break;
		    	          	 	}
		    	          	 case 4:
		    	          	 	{
		    	          	 		CancelOrder object=new Orders();
		    	          	 		object.cancel();
		    	          	 		break;
		    	          	 	}
		    	          	 case 5:
		    	          	 	{
		    	          	 		History.purchased();
		    	          	 		break;
		    	          	 	} 
		    	          	 case 6:
		    	          	 	{
		    	          	 		Help.help();
		    	          	 		break; 
		    	          	 		
		    	          	 	}
		    	          	 case 7:
		    	          	 	{
		    	          	 		System.out.println("you are successfully loged out");
		    	          	 		System.out.println("       ----------------LOGIN----------------");
		    	          	 		reset();
		    	          	 		Login.connection.close();
		    	          	 		OnlineGroceryShopping.productPurchase();
		    	          	 		break;
		    	          	 	}
		    	          	 default :
		                        {
		                        	System.out.println("ooops!  you are entered invalid option");
		                        	System.out.println("enter valid option :");
		                        	options();
		                        }
		    	          }
		    	   }
				catch(Exception reference)
		    	   {
		    		   System.out.print(reference);
		    	   }
		    }
	 	// This reset method is used to reset all the values declared in some classes    
	 	static void reset()
	       {
	    	   OrderProduct.finalTotal=0;
	    	   OrderProduct.date=null;
	    	   Login.userName=null;
	    	   OnlineGroceryShopping.forgot=0;
	       }
    }
			



