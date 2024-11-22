<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>home page</title>
</head>
<body>
<div style="margin:4%;color: black; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>
<div class="heading">
<%String log=request.getParameter("log");

if("login".equals(log))
{
%>
<h3 class="alert">you are already logedin!first logout to login</h3>
<%} %>
<%String message=request.getParameter("msg");

if("added".equals(message))
{
%>

  <h3 class="alert">Product added successfully!</h3> 
<%} %>
<%
if("exist".equals(message))
{
%>

 <h3 class="alert">Product already exist in you cart! Quantity  increased!</h3>
 <%} %>
<%
if("invalid".equals(message))
{
%>

<h3 class="alert">Something Went Wrong! Try Again!</h3> 
<%} %>
</div>
<%
try
{
Connection connection=ConnectionProvider.getCon();
Statement statement=connection.createStatement();
ResultSet result=statement.executeQuery("select * from product where active='yes';");
while(result.next())
{
%>
	    <div class="cards">
	        <div class="image">
	          <img Style="height:200px;" src="css/images/<%=result.getString(2)%>.jpg">
	        </div>
	        <div class="title">
	            <h1><%=result.getString(2)%></h1>

	        </div>
	        <div class="des">
	            <p>Price : <%=result.getString(4)%></p>
	            <a href="AddToCartServlet?id=<%=result.getString(1)%>"><button>addtocart</button></a>

	        </div>

	    </div>


<%}
}
catch(Exception reference)
{
System.out.println(reference);	
}
%>
 
</body>
</html>