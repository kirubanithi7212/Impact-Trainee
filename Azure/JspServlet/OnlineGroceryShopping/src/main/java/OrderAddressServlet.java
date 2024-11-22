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
 * Servlet implementation class OrderAddressServlet
 */
@WebServlet("/OrderAddressServlet")
public class OrderAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAddressServlet() {
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
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String mobileNumber=request.getParameter("mobileNumber");
		String paymentMethod=request.getParameter("paymentMethod");
		String transactionId=request.getParameter("transactionId");
		String status="bill";
		try
		{
			Connection connection=ConnectionProvider.getCon();
			PreparedStatement preparedStatement =connection.prepareStatement("update userdetails set address=?,city=?,state=?,country=?,mobilenumber=? where email=?;");
			{
				preparedStatement.setString(1,address);
				preparedStatement.setString(2,city);
				preparedStatement.setString(3,state);
				preparedStatement.setString(4,country);
				preparedStatement.setString(5,mobileNumber);
				preparedStatement.setString(6,email);
				preparedStatement.executeUpdate();
				
				PreparedStatement preparedStatementForCartUpdate=connection.prepareStatement("update cart set address=?,city=?,state=?,country=?,mobilenumber=?,orderdate=now(),deliverydate=DATE_ADD(orderdate,INTERVAL 1 DAY),paymentmethod=?,transactionid=?,status=? where email=? and address is NULL;");
				preparedStatementForCartUpdate.setString(1,address);
				preparedStatementForCartUpdate.setString(2,city);
				preparedStatementForCartUpdate.setString(3,state);
				preparedStatementForCartUpdate.setString(4,country);
				preparedStatementForCartUpdate.setString(5,mobileNumber);
				preparedStatementForCartUpdate.setString(6,paymentMethod);
				preparedStatementForCartUpdate.setString(7,transactionId);
				preparedStatementForCartUpdate.setString(8,status);
				preparedStatementForCartUpdate.setString(9,email);
				preparedStatementForCartUpdate.executeUpdate();
				response.sendRedirect("bill.jsp");
			}
		}
		catch(Exception reference)
		{
		System.out.println(reference);	
		}
}
}
