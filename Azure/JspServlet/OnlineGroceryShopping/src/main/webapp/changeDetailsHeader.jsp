<!DOCTYPE html>
<html>
<head>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style type="text/css">
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color:#ccc;
   color:black;
   text-align: center;
}
.topnav a:hover
{
cursor: pointer;
    color: rgb(191, 31, 31);
    font-size: 20px;
}
.topnav a
{
color: black;
    text-decoration: none;
    font-weight: bold;
}
.topnav{
   background-color:whitesmoke;
align-items: center;
display:flex;
 justify-content:space-between ;
padding-top: 40px;
    padding-left: 5%;
    padding-right: 10%;
    padding-bottom: 30px;
}
</style>
</head>
    <br>

             <%String email=(String)session.getAttribute("email"); %>
    <div class="topnav" style="position:fixed;width:90%; margin-left:-1%;margin-top:-2%;color-black;">
            
            <h2><a href="home2.jsp"><i class='fas fa-arrow-circle-left'>Back</i></a></h2>
            
            <a href="changePassword.jsp"><i class='fas fa-key'></i>Change Password </a>
            <a href="addOrChangeAddress.jsp"><i class='fas fa-map-marker-alt'></i>Add or change Address </a>
            <a href="changeMobileNumber.jsp"><i class='fas fa-phone'></i>Change Mobile Number </a>
             <h3><a href=""><i class='fas fa-user-alt'></i><% out.println(email);%> </a></h3>
          </div>
           <br>
