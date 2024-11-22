<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
.input {
  width: 60%;
  padding: 12px 20px;
  margin-left:20%;
  box-sizing: border-box;
  border: none;
  background-color:white;
  color: black;
  font-size: 16px;
}
input[type=text]:focus, textarea:focus,button:focus{
  outline: 4px solid orange;
  background-color:rgb(224, 255, 219);     /* oranges! yey */
}

textarea
{
	height: 250px;
	font-size: 16px;
}

.button {
  background-color: white; /* Green */
  border: none;
  color: black;
  padding: 12px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  transition-duration: 0.2s;
  cursor: pointer;
  margin-left:20%;
  font-size: 16px;
}

.button:hover {
  background-color:green;
  color: white;
}

hr
{
	width: 60%
}

h3
{
	text-align: center;
	color: white;
}

</style>
<title>Message Us</title>
</head>
<body style="margin-top:7%;background-color:black;">
<% String checkForCart=(String)session.getAttribute("email");
if(checkForCart==null)
{
	response.sendRedirect("login.jsp?check=notlogined");
}
else
{
%>
<div style="color: white; text-align: center;margin-top:6%; font-size: 30px;">Message Us <i class='fas fa-comment-alt'></i></div><br>
<%
String message=request.getParameter("msg");
if("valid".equals(message))
{
%>
<h3 style="text-align:center; color:yellow;">Message successfully sent. Our team will contact you soon!</h3>
<%} %>
<%
if("invalid".equals(message))
{
%>
<h3 style="text-align:center; ">Some thing Went Wrong! Try Again!</h3>
<%} %>
<form action="MessageUsServlet" method="post">
<input class="input" name="subject" type="text" placeholder="subject"required>
<hr>
<textarea class="input" name="body" type="text" placeholder="Enter Your Message"required></textarea>
<hr>
<button class="button" type="subject">send<i class="far fa-alt-circle-right"></i></button>
</form>
<br><br><br>
<%} %>
</body>
</html>