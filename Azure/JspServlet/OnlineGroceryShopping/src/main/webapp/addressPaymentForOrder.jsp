<%@page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@include file="footer.jsp" %>
<html>
<head>
<link rel="stylesheet" href="css/addressPaymentForOrder-style.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>PaymentPage</title>
<script >
if(window.history.forward(1) !=null)
	window.history.forward(1)
</script>
</head>
<body style="background-color:black;">
<br>
<table>
<thead>
<%
String email=session.getAttribute("email").toString();
int total=0,no=0;
try
{
	Connection connection =ConnectionProvider.getCon();
	Statement statement=connection.createStatement();
	ResultSet result=statement.executeQuery("select sum(total) from cart where email='"+email+"' and address is NULL;");
	while(result.next())
	{
	  total=result.getInt(1);
	}
%>
          <tr>
          <th scope="col"><a href="myCart.jsp"><i class='fas fa-arrow-circle-left'> Back</i></a></th>
            <th scope="col" style="background-color: yellow;">Total: <i class="fa fa-inr"></i><%out.println(total); %> </th>
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
          </tr>
        </thead>
        <tbody>
         <%
       ResultSet resultSet=statement.executeQuery("select * from product inner join cart on product.id=cart.productid and cart.email='"+email+"' and cart.address is NULL;");
      while(resultSet.next())
      {
      %>
          <tr>
          <%no=no+1;%>
           <td><%out.println(no); %></td>
            <td><%=resultSet.getString(2)%></td>
            <td><%=resultSet.getString(3)%></td>
            <td><i class="fa fa-inr"></i> <%=resultSet.getString(4)%></td>
            <td><%=resultSet.getString(8)%> </td>
            <td><i class="fa fa-inr"></i><%=resultSet.getString(10)%> </td>
            </tr>
         <%  }
         ResultSet resultForUserDetails=statement.executeQuery("select * from userdetails where email='"+email+"';");
         while(resultForUserDetails.next())
         {
         %>
        </tbody>
      </table>
      
<hr style="width: 100%">
<form  action="OrderAddressServlet"method="post">
 <div class="left-div">
 <h3>Enter Address</h3>
<input class="input-style"type="text" name="address" value="<%=resultForUserDetails.getString(7)%>" placeholder="Enter Your Address" required>
 </div>

<div class="right-div">
<h3>Enter city</h3>
<input class="input-style"type="text" name="city" value="<%=resultForUserDetails.getString(8)%>" placeholder="Enter Your City" required>
</div> 

<div class="left-div">
<h3>Enter State</h3>
<input class="input-style"type="text" name="state" value="<%=resultForUserDetails.getString(9)%>" placeholder="Enter Your State" required>
</div>

<div class="right-div">
<h3>Enter country</h3>
<input class="input-style"type="text" name="country" value="<%=resultForUserDetails.getString(10)%>" placeholder="Enter Your country" required>
</div>
<hr style="width: 100%">
<div class="left-div">
<h3>Select way of Payment</h3>
 <select class="input-style" name="paymentMethod">
 <option value="cash on delivery(COD)">Cash on delivery(COD)</option>
  <option value="Online Payment">Online Payment</option>
 </select>
</div>

<div class="right-div">
<h3>Pay online through any UPI apps</h3>
<input class="input-style"type="text" name="transactionId" placeholder="Enter Your Transaction ID" >
<h3 style="color:green">If you select online Payment then enter you transaction ID here otherwise leave this blank</h3>
</div>
<hr style="width: 100%">

<div class="left-div">
<h3>Mobile Number</h3>
<input class="input-style"type="text" name="mobileNumber" value="<%=resultForUserDetails.getString(3)%>" placeholder="Enter Your Mobile Number" required>

</div>
<div class="right-div">
<h3 style="color: red">If you enter wrong transaction id then your order will we can canceled!</h3>
<button class="button" type="submit">Proceed to generate Bill & Save <i class='far fa-arrow-alt-circle-right'></i></button>
<h3 style="color: pink;">All details are updated to your profile</h3>
</div>
</form>
<%
         }  
}
catch(Exception reference)
{
  System.out.println(reference);	
}
%>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      

</body>
</html>