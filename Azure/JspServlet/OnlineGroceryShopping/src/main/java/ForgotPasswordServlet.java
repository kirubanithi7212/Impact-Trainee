import project.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String mobileNumber=request.getParameter("mobileNumber");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		String newPassword=request.getParameter("newPassword");

		 int check=0;
		 try
		   {
			 Connection connection=ConnectionProvider.getCon();
			 Statement statement=connection.createStatement();
			 ResultSet result=statement.executeQuery("select * from userdetails where email='"+email+"' and mobileNumber='"+mobileNumber+"'and securityquestion='"+securityQuestion+"' and answer='"+answer+"';");
		     while(result.next())
		     {
		    	 check=1;
		    	 statement.executeUpdate("update userdetails set password='"+newPassword+"' where email='"+email+"';");
		    	 response.sendRedirect("forgotPassword.jsp?msg=done");
		     }
		     if(check==0)
		     {
		    	 response.sendRedirect("forgotPassword.jsp?msg=invalid");
		     }
		   }
		 catch(Exception reference)
		   {
			 System.out.println(reference);
			 
		   }
	}

}
