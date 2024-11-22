<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/forgotcss.css">
<title>ForgotPassword</title>
</head>
<body>
<%
   String message=request.getParameter("msg");
   if("done".equals(message))
   {
   %>
<h1 >Password Changed Successfully!</h1>
<%} %>
<%
if("invalid".equals(message))
{
	
%>
<h1 >Some thing Went Wrong! Try Again !</h1>
<%} %>
<div class="container">
  <div class="forgot">
     <form action="ForgotPasswordServlet"method="post">
     <input type="email" name="email" placeholder="enter email id "required>
     <input type="number" name="mobileNumber" placeholder="enter mobile number"required>
     <select name="securityQuestion"required>
      <option value="what is your best friend name ?">what is your best friend name ?</option>
      <option value="what was your first car ?">what was your first car ?</option>
      <option value="what is the name of your first pet name ?">what is the name of your first pet name ?</option>
      <option value="what is the name of the town where you are born ?">what is the name of the town where you are born ?</option>
     </select>
     <input type="text" name="answer" placeholder="enter answer"required>
     <input type="password" name="newPassword" placeholder="enter your new password to set"required>
     <input type="submit" value="save" >
     </form>
      <h2><a href="login.jsp">Login</a></h2>
  </div>
   </div>
</body>
</html>