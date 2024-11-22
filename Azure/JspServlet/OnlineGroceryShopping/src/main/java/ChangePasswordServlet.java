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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String confirmPassword=request.getParameter("confirmPassword");
		  if(!confirmPassword.equals(newPassword))
			   response.sendRedirect("changePassword.jsp?msg=notMatch");
		  else
		   {
		     int check=0;
		     try
		       {
		          Connection connection=ConnectionProvider.getCon();
				  Statement statement=connection.createStatement();
		          ResultSet result=statement.executeQuery("select * from userdetails where email='"+email+"' and password='"+oldPassword+"';");
		          while(result.next())
		            {
		               check=1;
		               statement.executeUpdate("update userdetails set password='"+newPassword+"' where email='"+email+"';");
					   response.sendRedirect("changePassword.jsp?msg=done");
		             }
		          if(check==0)
					response.sendRedirect("changePassword.jsp?msg=wrong");	
		        }
		      catch(Exception reference)
		        {
		          System.out.println(reference);	
		        }
		     }
	}

}
