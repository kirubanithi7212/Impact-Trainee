<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="changeDetailsHeader.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<title >Change Details</title>
<style>
body
{
  background-color:black;
  background-repeat:no-repeat;
  background-attachment:fixed;
  background-size:cover;
}
hr
{width:70%;}</style>
</head>
<body >
<!--background-image:url("css/images/back.jpg");-->
<% String checkForCart=(String)session.getAttribute("email");
if(checkForCart==null)
{
	response.sendRedirect("login.jsp?check=notlogined");
}
else
{
%>
<h2 style="color:white;margin-top:25%;text-align:center;">welcome to edit page<br><h2 style="color:white;text-align:center;">(You can edit your details by click the edit option at the header)</h2></h2>
<%
}
%>
</body>
</html>