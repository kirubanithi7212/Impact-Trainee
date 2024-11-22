<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="changeDetailsHeader.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<title>Edit Address</title><style type="text/css"> 
h4
{
    font-size:25px;
	text-align: center;
	color: white;
	text-shadow: 5px 5px 5px white;
	 
}
body
{
  background-color:black;
  background-repeat:no-repeat;
  background-attachment:fixed;
  background-size:cover;
}
</style>

</head>
<body>
<!--background-image:url("css/images/back.jpg");-->
<%
String message=request.getParameter("msg");
if("valid".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Address Successfully Updated !</h3>
<%} %>
<%
if("invalid".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Some thing Went Wrong! Try Again!</h3>
<%} %>
<%
try
{
Connection connection=ConnectionProvider.getCon();
Statement statement=connection.createStatement();
ResultSet result=statement.executeQuery("select * from userdetails where email='"+email+"';");
while(result.next())
{
%>
<form style="margin-top:110px;" action="AddOrChangeAddressServlet" method="post">
<h4>Enter Address</h4>
<input class="input-style"type="text" name="address" value="<%=result.getString(7) %>" placeholder="Enter Your Address" required> 
 <hr>
 <h4>Enter city</h4>
 <input class="input-style"type="text" name="city" value="<%=result.getString(8) %>" placeholder="Enter Your City" required>
<hr>
<h4>Enter State</h4>
<input class="input-style"type="text" name="state" value="<%=result.getString(9) %>" placeholder="Enter Your State" required>
<hr>
<h4>Enter country</h4>
<input class="input-style"type="text" name="country" value="<%=result.getString(10) %>" placeholder="Enter Your Country" required>
<hr>
 <button class="button" type="submit">Save<i class='far fa-arrow-alt-circle-right'></i></button>
 </form>
<%
}
}
catch(Exception reference)
{
System.out.println(reference);	
}

%>

</body>
<br><br><br><br>
</html>