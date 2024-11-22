<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="changeDetailsHeader.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>ChangePassword</title>
<style type="text/css"> 
body
{
  background-color:black;
  background-repeat:no-repeat;
  background-attachment:fixed;
  background-size:cover;
}
h4
{
    font-size:25px;
	text-align: center;
	color: white;
	text-shadow: 5px 5px 5px white;
	 
}</style>
</head>
<body>
<!--background-image:url("css/images/back.jpg");-->

<% String message=request.getParameter("msg"); 
if("notMatch".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">New password and Confirm password does not match!</h3>
<%} %>
<% 
if("wrong".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Your old Password is wrong!</h3>
<%} %>
<% 
if("done".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Password change successfully!</h3>
<%} %>
<% 
if("invalid".equals(message))
{
%>
<h3 style="text-align: center;margin-top:7%;margin-bottom-0%;"class="alert">Some thing went wrong! Try again!</h3>
<%} %>
<form style="margin-top:110px;"action="ChangePasswordServlet" method="post">

<h4 >Enter Old Password</h4>
 <input class="input-style" type="password" name="oldPassword" placeholder="Enter Your Old Password" required>
  <hr>
 <h4>Enter New Password</h4>
 <input class="input-style"type="password" name="newPassword" placeholder="Enter Your New Password" required>
 <hr>
<h4>Enter Confirm Password</h4>
<input class="input-style"type="password" name="confirmPassword" placeholder="Enter Your confirm Password" required>
<hr>
<button class="button" type="submit">save<i class='far fa-arrow-alt-circle-right'></i></button> 
</form>
</body>
<br><br><br>
</html>