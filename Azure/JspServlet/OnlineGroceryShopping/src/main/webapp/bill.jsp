<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/bill.css">
<title>Bill</title>
</head>
<body style="background-color:aquamarine;">
<%
String email=session.getAttribute("email").toString();
try
{
int total=0,sno=0;
Connection connection=ConnectionProvider.getCon();
Statement statement=connection.createStatement();
ResultSet result=statement.executeQuery("select sum(total) from cart where email='"+email+"' and status='bill';");
while(result.next())
{
	total=result.getInt(1);
}
ResultSet resultset=statement.executeQuery("select * from userdetails inner join cart where cart.email='"+email+"'and userdetails.email='"+email+"' and cart.status='bill';");
  while(resultset.next())
  {
%>
<h3>Online Grocery Shopping Bill</h3>
<hr>
<div class="left-div"><h3>Name: <%=resultset.getString(1) %> </h3></div>
<div class="right-div-right"><h3>Email: <% out.println(email); %>  </h3></div>
<div class="right-div"><h3>Mobile Number: <%=resultset.getString(20) %>  </h3></div>  

<div class="left-div"><h3>Order Date:  <%=resultset.getString(21) %> </h3></div>
<div class="right-div-right"><h3>Payment Method:  <%=resultset.getString(23) %> </h3></div>
<div class="right-div"><h3>Expected Delivery:<%=resultset.getString(22) %>   </h3></div> 

<div class="left-div"><h3>Transaction Id: <%=resultset.getString(24) %>  </h3></div>
<div class="right-div-right"><h3>City:  <%=resultset.getString(17) %> </h3></div> 
<div class="right-div"><h3>Address: <%=resultset.getString(16) %>  </h3></div> 

<div class="left-div"><h3>State: <%=resultset.getString(18) %>  </h3></div>
<div class="right-div-right"><h3>Country: <%=resultset.getString(19) %>  </h3></div>  

<hr>
<%break;} %>
	
	<br>
	
<table id="customers">
<h3>Product Details</h3>
  <tr>
    <th>S.No</th>
    <th>Product Name</th>
    <th>category</th>
    <th>Price</th>
    <th>Quantity</th>
     <th>Sub Total</th>
  </tr>
  <%
  ResultSet resultSetForSubTotal=statement.executeQuery("select * from cart inner join product where cart.productid=product.id and cart.email='"+email+"' and cart.status='bill';");
  while(resultSetForSubTotal.next())
  {
	  sno=sno+1;
  
  %>
  <tr>
    <td><%out.println(sno); %></td>
    <td><%=resultSetForSubTotal.getString(17)%></td>
    <td><%=resultSetForSubTotal.getString(18)%></td>
    <td><%=resultSetForSubTotal.getString(19)%></td>
    <td><%=resultSetForSubTotal.getString(3)%></td>
     <td><%=resultSetForSubTotal.getString(5)%></td>
  </tr>
  <tr>
<%} %>
</table>
<h3>Total:<%out.println(total); %> </h3>
<a href="FinishOrderServlet"><button  style=" margin-top: 30px;margin-bottom: 20px;margin-left:25%;"class="button left-button">Continue Shopping</button></a>
<br><br><br><br>
<%}
catch(Exception reference)
{
System.out.println(reference);	
}
%>
</body>
</html>