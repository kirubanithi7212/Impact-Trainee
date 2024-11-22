import project.ConnectionProvider;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String password=request.getParameter("password");
		if("kiruba@gmail.com".equals(email)&&"kiruba123".equals(password))
		   {
			HttpSession session=request.getSession();
			session.setAttribute("email",email);
			response.sendRedirect("admin/adminHome.jsp");
		   }
		else
		  {
			int logged=0;
			try
			{
				Connection connection=ConnectionProvider.getCon();
				Statement statement=connection.createStatement();
				ResultSet verify=statement.executeQuery("select * from userdetails where email='"+email+"'and password='"+password+"';");
				while(verify.next())
				{
					logged=1;
					HttpSession session=request.getSession();
					session.setAttribute("email",email);
					response.sendRedirect("home2.jsp");
				}
				if(logged==0)
				 {
					response.sendRedirect("login.jsp?msg=notexist");
				 }
			}
			catch(Exception reference)
			{
				System.out.println(reference);
				response.sendRedirect("login.jsp?msg=invalid");
			}
		  }
	}

}
