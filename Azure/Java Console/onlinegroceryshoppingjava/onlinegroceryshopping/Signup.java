/*
 Title       : Online Grocery Shopping
 Author      : Kirubanithi.v
 Created at  : October 13
 Updated at  : October 31
 Reviewed by :
 Reviewed at :
 */
package onlinegroceryshopping;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Signup 
      {
	    private String userId, password,name,gender,address,phoneNo,emailId,securityQuestion,answer;
	    // This  Method is used to signUp
	    static void signUp() 
	     {
	       	String loopForUserName ="true", loopForPassword ="true";
	       	Signup objectForSignup = new Signup();                                                  
	       	Scanner inputForSignup=new Scanner(System.in);
			Connection connection2=JdbcConnection.getConnection();
			System.out.println("            WELCOME TO REGISTER PAGE                ");
			System.out.println("Enter your name:");
	        System.out.println("Note : only Alphabets are allowded");
	        String nameOfUser=inputForSignup.nextLine();
	        objectForSignup.setName(nameOfUser);
	        System.out.println("Enter your gender");
	        System.out.println("Note :like  male , female and other");
	        String genderOfUser=inputForSignup.nextLine();
	        objectForSignup.setGender(genderOfUser);
	        System.out.println();
	        System.out.println("Enter your address : ");
	        String addressOfUser=inputForSignup.nextLine();
	        objectForSignup.setAddress(addressOfUser);
	        System.out.println();
	        System.out.println("Enter your phone number");
	        System.out.println("Note : carefully enter you mobile number :");
	        String userMobileNo=inputForSignup.nextLine();
	        objectForSignup.setPhoneno(userMobileNo);
	        System.out.println();
	        System.out.println("Enter your Emailid : ");
	        System.out.println("Note : carefully enter you emailid :");
	        String emailIdOfUser=inputForSignup.nextLine();
	        objectForSignup.setEmailId(emailIdOfUser);
	        System.out.println();
	        // getting valid userid
	        System.out.print("Note : \n 1. A userid must have at least eight characters.\n" +
                    "2. A userid consists of only letters , digits and special characters.\n" +
                    "3. A userid must contain at least two digits and one special character \n" +
                    "Input a userid (You are agreeing to the above Terms and Conditions.): \n");
	        while(loopForUserName.equals("true"))
	           {
	        	  System.out.println("Enter your userid :");
	        	  String userId=inputForSignup.nextLine();
	        	  if(isValidUserId(userId)) 
		             {
		        	    objectForSignup.setUserId(userId);
		        	    loopForUserName="false";
		             } 
	        	  else 
	        	  	 {
	        		    System.out.println("Not a valid userid:"+userId);
	        		    System.out.print("Note : \n 1. A userid must have at least eight characters.\n" +
		                           "2. A userid consists of only letters ,digits and special characters.\n" +
		                           "3. A userid must contain at least two digits and one special character(@,#,$,^)\n" +
		                           "Input a userid (You are agreeing to the above Terms and Conditions.): \n");
	        	  	 }
	           }
	        System.out.println();
	        // getting password
	        System.out.print("Note : \n 1. A password must have at least eight characters.\n" +
                    "2. A password consists of only letters and digits.\n" +
                    "3. A password must contain at least two digits \n" +
                    "Input a password (You are agreeing to the above Terms and Conditions.): \n ");
	        while(loopForPassword.equals("true"))
	           {
	              System.out.println("Enter your password:");
	              String passwordCheck=inputForSignup.nextLine();
	              if(isValidPassword(passwordCheck)) 
	                 {
	            	     objectForSignup.setPassword(passwordCheck);
	            	     loopForPassword="false";
	                 } 
	              else 
	                 {
	            	     System.out.println("Not a valid password:"+passwordCheck);
	            	     System.out.print("Note : \n 1. A password must have at least eight characters.\n" +
	                           "2. A password consists of only letters and digits.\n" +
	                           "3. A password must contain at least two digits \n" +
	                           "Input a password (You are agreeing to the above Terms and Conditions.): \n");
	                 }
	           }
	        //sequrity question
	        String questions[]=new String[]{"What is the name of your favorite pet?","What was the name of your elementary school?","What was your favorite food as a child?","What year was your father born?","What was your favorite subject in high school?"};
	        for(int index=0;index<questions.length;index++)
	           {	
	               System.out.println((index+1)+"."+questions[index]);
	           }
	        int questionChoice;
	        String answerForQuestion;
	        System.out.println("Choose any one question no : ");
	         questionChoice=inputForSignup.nextInt();
	        System.out.println();
	        System.out.print("Enter your answer for "+questions[questionChoice-1]);
	        answerForQuestion=inputForSignup.next();
	        objectForSignup.setSecurityQuestion(questions[questionChoice-1]);
	        objectForSignup.setAnswer(answerForQuestion);
	        try 
	           {
	        	  String userdetails=" insert into userdetails(name,gender,address,phoneno,emailid,userid,password,securityquestion,answer)values(?,?,?,?,?,?,?,?,?);";
	        	  PreparedStatement register= connection2.prepareStatement(userdetails);	
	        	  register.setString(1,objectForSignup.getName());
	        	  register.setString(2,objectForSignup.getGender());
	        	  register.setString(3,objectForSignup.getAddress());
	        	  register.setString(4,objectForSignup.getPhoneNo());
	        	  register.setString(5,objectForSignup.getEmailId());
	        	  register.setString(6,objectForSignup.getUserId());
	        	  register.setString(7,objectForSignup.getPassword());
	        	  register.setString(8,objectForSignup.getSecurityQuestion());
	        	  register.setString(9,objectForSignup.getAnswer());
	        	  register.executeUpdate();
	        	  connection2.close();
	        	  System.out.println("\n        YOU ARE SUCCESSFULLY REGISTERED        ");
	           }
	        catch(SQLException exception) 
	           {
	    	      System.out.println(exception);
	    	      System.out.println("data not added");
	           }
	        
	        OnlineGroceryShopping.productPurchase();
	     } 
		 
	    // for validating password 
	    public static boolean isValidPassword(String password) 
	      {
	    	int characterCount = 0,numberCount = 0;
	    	char singleCharacter;
	    	if (password.length() < 8)
	    	   {   
	        	   return false;
	    	   }
            for(int index = 0; index < password.length(); index++) 
               {
                  singleCharacter = password.charAt(index);
                  if (isNumeric(singleCharacter)) 
                	  numberCount++;
	              else if (isLetter(singleCharacter)) 
	            	  characterCount++;
	              else return false;
	            }
            return (characterCount >= 2 && numberCount >= 2);
	      }

	   // for validating userid
	   public static boolean isValidUserId(String password) 
	      {
             if (password.length() < 8) 
	        	   return false;
             int characterCount = 0, numberCount = 0,specialCharacterCount=0;
	         for (int index = 0; index < password.length(); index++) 
	            {
	        	   char character = password.charAt(index);
                   if (isNumeric(character)) 
                        { numberCount++; }
	               else if (isLetter(character)) 
	                    {characterCount++;}
	               else if(isSpecialCharacter(character)) 
	                    { specialCharacterCount++; }
	               else return false;
	            }
	         return ((characterCount >= 2 && numberCount >= 2) && specialCharacterCount >=1);
	      }
	    
	    // methods for checking the password and username letters
	   static boolean isLetter(char letter) 
	      {
		   letter = Character.toUpperCase(letter);
	        return (letter >= 'A' && letter <= 'Z');
	      }
	   static boolean isNumeric(char numeric) 
          {
            return (numeric >= '0' && numeric <= '9');
	      }
	   static boolean isSpecialCharacter(char SpecialCharacter) 
	      {
            return (SpecialCharacter == '@' || SpecialCharacter == '$'||SpecialCharacter == '#'||SpecialCharacter == '^');
	      }
	   // getter setter methods for userId and password
	public String getUserId() 
	      {
		     return userId;
	      }
	public void setUserId(String userId) 
	  	  {
			this.userId = userId;
	  	  }
	public String getPassword() 
		  {
			return password;
		  }
	public void setPassword(String password) 
	  	  {
			this.password = password;
	  	  }
	public String getName() 
	      {
			return name;
	      }
	public void setName(String name) 
	      {
			this.name = name;
	      }
	public String getGender() 
	      {
			return gender;
		  }
   public void setGender(String gender) 
    	  {
			this.gender = gender;
    	  }
   public String getAddress() 
     	  {
			return address;
     	  }
   public void setAddress(String address) 
   	      {
			this.address = address;
   	      }
   public String getPhoneNo() 
          {
			return phoneNo;
          }
   public void setPhoneno(String phoneNo) 
    	  {
			this.phoneNo = phoneNo;
    	  }
   public String getEmailId() 
          {
			return emailId;
          }
   public void setEmailId(String emailId) 
   	      {
			this.emailId = emailId;
   	      }
   public String getAnswer() 
          {
		 	return answer;
          }
   public void setAnswer(String answer) 
   		  {
			this.answer = answer;
   		  }
   public String getSecurityQuestion() 
   		  {
			return securityQuestion;
   		  }
   public void setSecurityQuestion(String securityQuestion) 
   	      {
			this.securityQuestion = securityQuestion;
   	      }
   public static void forgoted()
         {
	          Scanner inputForForgot=new Scanner(System.in);
	          System.out.println("enter your userid : ");
	          String userIdForForgot=inputForForgot.nextLine();
	          String questionFromDb="notfound";
	          try
	             {
 		    	    Statement statementForForgot=Login.connection.createStatement();
			    	ResultSet question= statementForForgot.executeQuery("Select securityquestion from userdetails where userid='"+userIdForForgot+"';");
			    	while(question.next())
			    	    {
			    		     questionFromDb=question.getString(1); 
			    		     System.out.println(questionFromDb);
			    	    }
	             }
	          catch(Exception reference)
	             {  
	        	  System.out.println("password is not changed");
	             }
			    	if(questionFromDb.equals("notfound"))
			    	 {
			    		System.out.println(" Userid is wrong ");
			    		forgoted();
			    	 }
			    	else
			    	 {
			    		System.out.println("enter answer for above question");
				    	String answerForUpdate=inputForForgot.nextLine();
				    	String answerFromDataBase=null,exceptionCome="no";
				    	try
				    	{
				    	Statement statementForAnswer=Login.connection.createStatement();
				    	ResultSet answerForQuestion= statementForAnswer.executeQuery("Select answer from userdetails where userid='"+userIdForForgot+"';");
				    	while(answerForQuestion.next())
			    	    {
			    		   answerFromDataBase=answerForQuestion.getString(1);
			    	    }
				    	}
				    	 catch (SQLException exception) 
			             {
						    System.out.println("Something Wrong");
						    OnlineGroceryShopping.productPurchase();
						    exceptionCome="yes";
					     }
				    	if(exceptionCome!="yes")
				    	{
				    	if(answerFromDataBase.equals(answerForUpdate))
				    	  {
				    		changing(userIdForForgot);
				    		OnlineGroceryShopping.productPurchase();
				    	  }
				    	else
				    	  {
				    		System.out.println("Answer not match , again try");
				    		forgoted();
				    	  }
				    	}
			    	 }
			     } 
	         
         
   public static void changing(String userIdForForgot)
         {
	          Scanner inputForChanging= new Scanner(System.in);
	          System.out.println("Enter new password : ");
	          String newPassword1=inputForChanging.nextLine();
	          System.out.println("Re enter the password : ");
	          String newPassword2=inputForChanging.nextLine();
	          if(newPassword1.equals(newPassword2))
	              { 
	        	  if(isValidPassword(newPassword1)) 
		             {
	        		  try 
	        	      {
						String resetingPassword="update userdetails set password='"+newPassword1+"' where userid='"+userIdForForgot+"';";
		        	    PreparedStatement reseting;
						reseting = Login.connection.prepareStatement(resetingPassword);
						reseting.executeUpdate();
						System.out.println("successfully changed");
					   } 
	        	    catch (SQLException exception) 
	        	       {
	        	    	System.out.println("Something Wrong , password is not changed");
	        	    	OnlineGroceryShopping.productPurchase();
	        	        
					   } 
		             } 
	        	  else 
	        	  	 {
	        		  System.out.println("Not a valid password:"+newPassword1);
	            	  System.out.print("Note : \n 1. A password must have at least eight characters.\n" +
	                           "2. A password consists of only letters and digits.\n" +
	                           "3. A password must contain at least two digits \n" +
	                           "Input a password (You are agreeing to the above Terms and Conditions.): \n");
	            	  changing(userIdForForgot); 
	        	  	 }
	              }
	          else
	            {
	        	  System.out.println(" password not match , please enter correctly");
	        	  changing(userIdForForgot);
	            }
         }
      }