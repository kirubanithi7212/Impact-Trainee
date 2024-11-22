
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/logincss.css">
<title>LoginPage</title>
</head>
<body  class="login">
<%
  String check=request.getParameter("check");
  if("notlogined".equals(check))
  {
  %>
  <h1>First You Want To Login</h1>
  <%} %>
  <%
  if("logouted".equals(check))
  {
  %>
  <h1>You Are successfully logouted</h1>
  <%} %>
  <%
  String message=request.getParameter("msg");
  if("notexist".equals(message))
  {
  %>
  <h1>Incorrect Username or Password</h1>
<%} %>
<% if("invalid".equals(message)) 
{
%>
<h1 >Some thing Went Wrong! Try Again !</h1>
<%} %>
<% String log=(String)session.getAttribute("email");
if(log!=null)
{%>
	
	<%
	response.sendRedirect("home2.jsp?log=login");
	 %>
<%}
else
{
%>
<%
}
%>
<div class="container">
  <div style="float: left;width: 300px;padding: 30px 20px;background-color: white;text-align: center;border-radius: 5px 0 0 5px;">
  <form action="LoginServlet"method="post">
  <input style="
  display: block;
  margin: 0 auto;
  width: 80%;
  border: 0;
  border-bottom: 1px solid rgba(0,0,0,.2);
  height: 45px;
  line-height: 45px;  
  margin-bottom: 10px;
  font-size: 1em;
  color: black;
"type="text" name="email" placeholder="enter your email" pattern=".+@gmail\.com" title="Please enter valid email id"required>
  <input style="
  display: block;
  margin: 0 auto;
  width: 80%;
  border: 0;
  border-bottom: 1px solid rgba(0,0,0,.2);
  height: 45px;
  line-height: 45px;  
  margin-bottom: 10px;
  font-size: 1em;
  color: black;
"type="password" name="password" placeholder="enter your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"title="Password must contain at least one number and one uppercase and lowercase letter,and atleast 8 or more characters needed"required>
  <input type="submit" value="login">
  </form>
      <h2><a href="signup.jsp">SignUp</a></h2>
       <h2><a href="forgotPassword.jsp">Forgot Password?</a></h2>
  </div>
</div>

</body>
</html>