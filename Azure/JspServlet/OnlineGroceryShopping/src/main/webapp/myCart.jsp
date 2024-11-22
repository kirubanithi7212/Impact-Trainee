<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<title>My Cart</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" href="css/card.css">
</head>
<body Style="margin-top:6%;">
<% String checkForCart=(String)session.getAttribute("email");
if(checkForCart==null)
{
	response.sendRedirect("login.jsp?check=notlogined");
}
else
{
%>
<div style="color: white; text-align: center; font-size: 30px;margin-top:7px;">My Cart <i class='fas fa-cart-arrow-down'></i></div>
<div class="heading">
<% 
String message=request.getParameter("msg");
if("notpossible".equals(message))
{
%>
<h3 class="alert">There is only one Quantity! So click on remove!</h3>
<%} %>
<% 
if("increased".equals(message))
{
%>
<h3 class="alert">Quantity  Increased Successfully!</h3>
<%} %>
<% 
if("decreased".equals(message))
{
%>
<h3 class="alert">Quantity  Decreased Successfully!</h3>
<%} %>
<% 
if("removed".equals(message))
{
%>
<h3 class="alert">Product Successfully Removed!</h3>
<%
} 
%>

<%
int total=0;
try
{
	Connection connection =ConnectionProvider.getCon();
	Statement statement=connection.createStatement();
	ResultSet result=statement.executeQuery("select sum(total) from cart where email='"+email+"' and address is NULL;");
	while(result.next())
	{
	  total=result.getInt(1);
	}

%>
              <% if(total>0)
             {
             %>
             <h3 style="text-align:right;"><a style="margin-right:2%;width:11%;color:white;border-margin:2px;text-align:center;color:black;margin-top:11px;background-color:white;" href="addressPaymentForOrder.jsp">Proceed to order</a></h3>
             <%} %>
             <h3 style="width:8%;text-align:left;margin-top:-2%;background-color:white;color:black;border-margin:2px;margin-left:2%;text-align:center;">Total: <i class="fa fa-inr"></i><%out.print(total); %></h3>
             
       
        </div>
      <%
       ResultSet resultset=statement.executeQuery("select * from product inner join cart on product.id=cart.productid and cart.email='"+email+"' and cart.address is NULL;");
      while(resultset.next())
      {
      %>
            
            <div class="cards">
        <div class="image">
          <img Style="height:200px;" src="css/images/<%=resultset.getString(2)%>.jpg">
        </div>
        <div class="title">
            <h1><%=resultset.getString(2)%></h1>
        </div>
        <div class="des">
            <p>price:<i class="fa fa-inr"></i><%=resultset.getString(4)%></p>
        </div>
        <div class="increase" style="text-align: center;"><a href="IncreaseDecreaseQuantityServlet?id=<%=resultset.getString(1)%>&quantity=increase"><i class='fas fa-plus-circle'></i></a><div style="height: 20px;width: 30px;border: 1px solid black;display:inline-block;text-align: center;"> <%=resultset.getString(8)%> </div><a href="IncreaseDecreaseQuantityServlet?id=<%=resultset.getString(1)%>&quantity=decrease"><i class='fas fa-minus-circle'></i></a></div>
        <div class="des">
            <p>Subtotal:<i class="fa fa-inr"></i> <%=resultset.getString(10)%></p>
        </div>
        <div class="des">
            <a href="RemoveFromCartServlet?id=<%=resultset.getString(1)%>"> <button> <i class='fas fa-trash-alt'></i>remove</button></a>
        </div>
    </div>
                   
<%
 }
}
catch(Exception reference)
{
System.out.println(reference);	
}
%>
      <br>
      <br>
      <br>
<%} %>
</body>
</html>