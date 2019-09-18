<%@ page import="java.io.*"%>
<%@ page import="java.util.List"%>
<%@ page
	import="com.training.ordmgmtprojcorejava.factory.ServiceObjectFactory"%>
<%@ page import="com.training.ordmgmtprojcorejava.model.Item"%>
<%@ page import="com.training.ordmgmtprojcorejava.model.Order"%>
<%@ page
	import="com.training.ordmgmtprojcorejava.service.CustomerService"%>
<%@ page import="com.training.ordmgmtprojcorejava.service.OrderService"%>
<%@ page import="com.training.ordmgmtprojcorejava.service.ItemService"%>
<HTML>
<HEAD>
<TITLE>Customer Login</TITLE>
</HEAD>

<BODY>
<H2>Customer Login Page</H2>
<br>
<%
String loginMsg= (String)session.getAttribute("loginFailed");
%>
 <SCRIPT LANGUAGE="JavaScript">
 var loginMsg = '<%=loginMsg%>';
 if(loginMsg.length>0 && loginMsg!="null"){
  alert(loginMsg);
  }

 function validateUserDetails()
 {
     var userName = document.userDetails.userName.value;
     var password = document.userDetails.password.value;
    
     if (userName == "") {
         alert("User Name can not be empty.");                 
         return false;
                       
     } else  if (password == "") {
         alert("Password can not be empty.");                  
         return false;
     }                 
     else
     {
       document.userDetails.submit();
     } 
 }
 
 </SCRIPT>
<form name="login" action="loginRead.jsp">
Username: <input type="text" name="username" placeholder="username">
<br><br>
Password: <input type="text" name="password" placeholder="password">
<br><br>
<input type="submit" name="submit" value="Login">
</form>
</BODY>
</HTML>