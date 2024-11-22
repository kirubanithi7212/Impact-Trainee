<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signupcss.css">
<title>SignupPage</title>
</head>
<body>
<%
String message=request.getParameter("msg");
if("valid".equals(message))
{
%>
<h1>successfully registered</h1>
<% }%>	
<% 
if("invalid".equals(message))
{
%>

<h1>Some thing Went Wrong! Try Again !</h1>
<%} %>
<div id='container' >

  <div class='signup'>
  <form action="SignupServlet" method="post">
     <input type="text" name="name" placeholder="enter your name here" required>
     <input type="text" name="email" pattern=".+@gmail\.com" title="Please enter valid email id"placeholder="enter your email id" >
     <input type="text" name="password" placeholder="enter your password"pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"title="Password must contain at least one number and one uppercase and lowercase letter,and atleast 8 or more characters needed" required>
     <input type="tel" name="mobileNumber" placeholder="+91**********" required>
     <select name="securityQuestion"required>
      <option value="what is your best friend name ?">what is your best friend name ?</option>
      <option value="what was your first car ?">what was your first car ?</option>
      <option value="what is the name of your first pet name ?">what is the name of your first pet name ?</option>
      <option value="what is the name of the town where you are born ?">what is the name of the town where you are born ?</option>
     </select>
     <input type="text" name="answer" placeholder="enter your answer here" required>
     
     <input type="submit" value="signup">
     </form>
      <h2><a href="login.jsp">Login</a></h2>
  </div> 
  </div>   


</body>
</html>