import project.ConnectionProvider;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class IncreaseDecreaseQuantityServlet
 */
@WebServlet("/IncreaseDecreaseQuantityServlet")
public class IncreaseDecreaseQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreaseDecreaseQuantityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<h1>"+email+"</h1>");
		String id=request.getParameter("id");
		String increamentOrDecreament=request.getParameter("quantity");
		int price=0,total=0,quantity=0;
		try
		{
		Connection connection=ConnectionProvider.getCon();
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery("select * from cart where email='"+email+"' and productid='"+id+"' and address is NULL;");
		while(result.next())
		{
			price=result.getInt(4);
			total=result.getInt(5);
			quantity=result.getInt(3);
			if(quantity==1 && "decrease".equals(increamentOrDecreament))
			{
			   response.sendRedirect("myCart.jsp?msg=notpossible");
			}
			else if(quantity!=1 && "decrease".equals(increamentOrDecreament))
			{
				total=total-price;
				quantity=quantity-1;
				statement.executeUpdate("update cart set total='"+total+"',quantity='"+quantity+"' where email='"+email+"' and productid='"+id+"' and address is NULL;");
				response.sendRedirect("myCart.jsp?msg=decreased");
			}
			else
			{
				total=total+price;
				quantity=quantity+1;
				statement.executeUpdate("update cart set total='"+total+"',quantity='"+quantity+"' where email='"+email+"' and productid='"+id+"' and address is NULL;");
				response.sendRedirect("myCart.jsp?msg=increased");
			}
		}
		}
		catch(Exception reference)
		{
			System.out.println(reference);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

}
}
