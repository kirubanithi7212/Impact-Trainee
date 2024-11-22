import project.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("email");
		if(email!=null)
		{
		String productid=request.getParameter("id");
		int quantity=1;
		int productprice=0;
		int producttotal=0;
		int carttotal=0;

		int a=0;
		try
		{
			Connection connection=ConnectionProvider.getCon();
			Statement statement=connection.createStatement();
			ResultSet result=statement.executeQuery("select * from product where id='"+productid+"';");
			while(result.next())
			{
			productprice=result.getInt(4);
			producttotal=productprice;
			}
			ResultSet resultforrepeat=statement.executeQuery("select * from cart where productid='"+productid+"' and email='"+email+"'and address is NULL");
		    while(resultforrepeat.next())
		    {
		    	carttotal=resultforrepeat.getInt(5);
		    	carttotal=carttotal+producttotal;
		    	quantity=resultforrepeat.getInt(3);
		    	quantity=quantity+1;
		    	a=1;
		    }
		    if(a==1)
		    {
		    	
		    	statement.executeUpdate("update cart set total='"+carttotal+"',quantity='"+quantity+"' where productid='"+productid+"' and email='"+email+"' and address is NULL;");
		    	response.sendRedirect("home2.jsp?msg=exist");
		    }
		    if(a==0)
		    {
		    	PreparedStatement preparesstatement=connection.prepareStatement("insert into cart(email,productid,quantity,price,total) values(?,?,?,?,?)");
		    	preparesstatement.setString(1,email);
		    	preparesstatement.setString(2,productid);
		    	preparesstatement.setInt(3,quantity);
		    	preparesstatement.setInt(4,productprice);
		    	preparesstatement.setInt(5,producttotal);
		    	preparesstatement.executeUpdate();
		    	response.sendRedirect("home2.jsp?msg=added");
		    }
		}
		catch(Exception reference)
		{
			System.out.println("beti");
			response.sendRedirect("home2.jsp?msg=invalid");
		}
		}
		else
		{
			response.sendRedirect("login.jsp?check=notlogined");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
