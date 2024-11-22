/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : October 13
 Updated at  : October 31
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
class Help
   {
	  //encapsulation is used here
	  private String helpFromUser;
	  static Scanner input= new Scanner(System.in);	
	  // this method is used to get  request or complaint from user	
	  static void help() 
	     {
		    try 
		      {
			     String Query;
			     System.out.println("Hello sir/madam");
				 System.out.println("What can i help for you ?");
				 Help objectForHelp = new Help();// object
				 objectForHelp.setHelpFromUser(input.nextLine());			
	             Query=" insert into help(help,userid)values(?,?);";
	    		 PreparedStatement complaint= Login.connection.prepareStatement(Query);	
	    	     complaint.setString(1,objectForHelp.getHelpFromUser());
	    	     complaint.setString(2,Login.userName);
	    	     complaint.execute();
	    	     System.out.println("Thanking for using our website \n your request is accepted and we will check your request\n responce will be in few minutes");
	    	     Menu.options();
		      } 
		    catch (SQLException exception) 
		      {
				 System.out.println(exception);
		      }	
	     }
	  public String getHelpFromUser() 
	     {
		    return helpFromUser;
	     }
	  public void setHelpFromUser(String helpFromUser) 
	     {
		    this.helpFromUser = helpFromUser;
	     }
   }
	
  
	
