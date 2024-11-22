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
 * Servlet implementation class MessageUsServlet
 */
@WebServlet("/MessageUsServlet")
public class MessageUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageUsServlet() {
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
		String subject=request.getParameter("subject");
		String body=request.getParameter("body");
		try
		{
			Connection connection=ConnectionProvider.getCon();
			PreparedStatement preparedStatement=connection.prepareStatement("insert into message(email,subject,body) value(?,?,?);");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,subject);
			preparedStatement.setString(3,body);
			preparedStatement.executeUpdate();
			response.sendRedirect("messageUs.jsp?msg=valid");
		}
		catch(Exception reference)
		{
			response.sendRedirect("messageUs.jsp?msg=invalid");
			System.out.println(reference);	
		}
	}

}
