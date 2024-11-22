<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="changeDetailsHeader.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Change Mobile Number</title>
<style type="text/css"> 

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
if("done".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Your Mobile Number successfully changed!</h3>
<%} %>
<%
if("wrong".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Your Password is wrong!</h3>
<%} %>
<form style="margin-top:110px;"action="ChangeMobileNumberServlet" method="post">
 
 <h4>Enter Your New Mobile Number</h4>
 <input class="input-style" type="number" name="mobileNumber" placeholder="Enter Your New Mobile Number" required>
 <hr>
<h4>Enter Password (For Security)</h4>
 <input class="input-style" type="password" name="password" placeholder="Enter Your Password(For Security Reason)" required>

<hr>
 <button  class="button" type="submit">Save <i class='far fa-arrow-alt-circle-right'></i></button>
</form>
</body>
<br><br><br>
</html>