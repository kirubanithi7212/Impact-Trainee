import project.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobileNumber=request.getParameter("mobileNumber");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		String password=request.getParameter("password");
		String address="";
		String city="";
		String state="";
		String country="";

		try{
			Connection connection=ConnectionProvider.getCon();
			PreparedStatement statement=connection.prepareStatement("insert into userdetails values(?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1,name);
			statement.setString(2,email);
			statement.setString(3,mobileNumber);
			statement.setString(4,securityQuestion);
			statement.setString(5,answer);
			statement.setString(6,password);
			statement.setString(7,address);
			statement.setString(8,city);
			statement.setString(9,state);
			statement.setString(10,country);
			statement.executeUpdate();
			
			response.sendRedirect("signup.jsp?msg=valid");
			
		}
		catch(Exception reference)
		{
			System.out.print(reference);
			response.sendRedirect("signup.jsp?msg=invalid");
			}
	}

}
