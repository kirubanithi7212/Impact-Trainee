<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/home-style.css">
<title>MyOrders</title>
</head>
<body Style="margin-top:9%;">
<% String checkForCart=(String)session.getAttribute("email");
if(checkForCart==null)
{
	response.sendRedirect("login.jsp?check=notlogined");
}
else
{
%>
<div style="color:white; text-align: center; font-size: 30px;">My Orders <i class='fab fa-elementor'></i></div>
<table >
        <thead>
          <tr style="background-color:yellow;">
            <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">category</th>
            <th scope="col"><i class="fa fa-inr"></i>  Price</th>
            <th scope="col">Quantity</th>
            <th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
            <th scope="col">Order Date And Time</th>
             <th scope="col">Delivery Expected Before</th>
             <th scope="col">Payment Method</th>
              <th scope="col">Status</th>
              
          </tr>
        </thead>
        <tbody>
<%
int sno=0;
try
{
Connection connection =ConnectionProvider.getCon();
Statement statement=connection.createStatement();
ResultSet result=statement.executeQuery("select * from cart inner join product where cart.productid=product.id and email='"+email+"' and cart.orderdate is not null order by orderdate desc;");
while(result.next())
{
	sno=sno+1;
%>
          <tr>
            <td><% out.println(sno); %></td>
            <td><%=result.getString(17) %></td>
            <td><%=result.getString(18) %></td>
            <td><i class="fa fa-inr"></i> <%=result.getString(19) %></td>
            <td><%=result.getString(3) %></td>
            <td><i class="fa fa-inr"></i> <%=result.getString(5) %></td>
             <td><%=result.getString(11) %></td>
              <td><%=result.getString(12) %></td>
               <td><%=result.getString(13) %></td>
               <td><%if("processing".equals(result.getString(15))) {%>
               <a href="CancelOrderServlet?message=<%=result.getString(11)%>"><button>cancel</button></a>
               <%}else{%>
               <%=result.getString(15) %>
               <%} %>
               </td>
            </tr>
         <%}}
catch(Exception reference)
{
  System.out.println(reference);	
}
%>
        </tbody>
      </table>
      <br>
      <br>
      <br>
<%} %>
</body>
</html>