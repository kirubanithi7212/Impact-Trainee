
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
    <!--Header-->
    <br>
    <div class="topnav">
    <%String email=(String)session.getAttribute("email");
    %>
    
            <center><h2 style="color:black;">Online Grocery Shopping</h2></center>
            <h2><a href=""><i class='fas fa-user-alt'></i><%if(email!=null){out.println(email);} %> </a></h2>
            <a href="home2.jsp"><i class="fa fa-institution"></i>Home</a>
            <a href="myCart.jsp">My Cart</a>
            <a href="myOrders.jsp"><i class='fab fa-elementor'></i>My Orders  </a>
            <a href="changeDetails.jsp"><i class="fa fa-edit"></i>Change Details </a>
            <a href="messageUs.jsp"><i class='fas fa-comment-alt'></i>Message Us </a>
            <a href="about.jsp"><i class="fa fa-address-book"></i>About </a>
             <a href="login.jsp">Login</a>
            <a href="LogoutServlet">Logout </a>
            <div class="search-container">
            <form style="margin-top:25px;"action="searchHome.jsp" method="post">
            <input type="text" placeholder="Search" name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
            </form>
             
               
             
            </div>
          </div>
           <br>
