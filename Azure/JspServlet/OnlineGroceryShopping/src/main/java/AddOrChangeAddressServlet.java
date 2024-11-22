import project.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddOrChangeAddressServlet
 */
@WebServlet("/AddOrChangeAddressServlet")
public class AddOrChangeAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrChangeAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		try
		{
			Connection connection=ConnectionProvider.getCon();
			PreparedStatement preparedStatement=connection.prepareStatement("update userdetails set address=?,city=?,state=?,country=? where email=?;");
			preparedStatement.setString(1,address);
			preparedStatement.setString(2,city);
			preparedStatement.setString(3,state);
			preparedStatement.setString(4,country);
			preparedStatement.setString(5,email);
			preparedStatement.executeUpdate();
			response.sendRedirect("addOrChangeAddress.jsp?msg=valid");
		}
		catch(Exception reference)
		{
		System.out.println(reference);
		response.sendRedirect("addOrChangeAddress.jsp?msg=invalid");
		}
	}
	}


