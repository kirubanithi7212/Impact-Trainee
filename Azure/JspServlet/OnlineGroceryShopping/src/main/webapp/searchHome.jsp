<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/card.css">
<title>HomePage</title>
</head>
<body style="margin-top:6%;">
<%
int a=0;
try
{
String search=request.getParameter("search");
Connection connection=ConnectionProvider.getCon();
Statement statement=connection.createStatement();
ResultSet result=statement.executeQuery("select * from product where name like'%"+search+"%' or category like '%"+search+"%' and active='yes';");
while(result.next())
{
	a=1;

%>
         <div class="cards">
	        <div class="image">
	          <img Style="height:200px;"src="css/images/<%=result.getString(2)%>.jpg">
	        </div>
	        <div class="title">
	            <h1><%=result.getString(2)%></h1>

	        </div>
	        <div class="des">
	            <p>Price : <%=result.getString(4)%></p>
	            <a href="AddToCartServlet?id=<%=result.getString(1)%>"><button>addtocart</button></a>

	        </div>

	    </div>
<% }
}
catch(Exception reference)
{
System.out.println(reference);	
}
%>
  <%
  if(a==0)
  {
  %>    	
	<h1 style="color:white; text-align: center;">Nothing to show</h1>
	<%} %>
      <br>
      <br>
      <br>
      <div class="footer">
          <p>All right reserved by KitZone</p>
      </div>

</body>
</html>