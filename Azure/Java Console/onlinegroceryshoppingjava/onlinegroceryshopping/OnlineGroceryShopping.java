/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : October 13
 Updated at  : November 06
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.util.Scanner;
public class OnlineGroceryShopping
    {
	   static Scanner input = new Scanner(System.in);
	   public static void main(String[] args) 
	      {
		   productPurchase();
	      }
	   public static int forgot;
	   public static void productPurchase()
		 {
			if(forgot==3)
			  {
				forgot=0;
				System.out.println("type yes for password change (or) No  for contineu login");
				String choiceForForgot = input.next();
				if(choiceForForgot.equals("yes"))
				   {
					Signup.forgoted();
				   }
				else
				   {
					productPurchase();
				   }
			  }
			else
			  {
				System.out.println("\n		                 1).login                ");
				System.out.println("		                                         ");
				System.out.println("		                 2).register             ");
				System.out.println("		                                         ");
				System.out.println("   ENTER YOUR CHOICE NUMBER :   ");
				String choiceForLoginOrRegister;
				choiceForLoginOrRegister = input.next();
			    	if(choiceForLoginOrRegister.equals("1"))
			    		{
			    			Login.logIn();
			    		}
			    	else if(choiceForLoginOrRegister.equals("2"))
			    		{
			    			Signup.signUp();
			    		}
			    	else
			    		{
			    			System.out.println("   you are entered invalid option , please select valid one   ");
			    			productPurchase();
			    		}
			  }
		 }
    }
