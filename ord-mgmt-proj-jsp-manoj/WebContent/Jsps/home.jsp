<%@ page
	import="com.training.ordmgmtprojcorejava.factory.ServiceObjectFactory"%>
<%@ page import="com.training.ordmgmtprojcorejava.model.Item"%>
<%@ page import="com.training.ordmgmtprojcorejava.model.Order"%>
<%@ page
	import="com.training.ordmgmtprojcorejava.service.CustomerService"%>
<%@ page import="com.training.ordmgmtprojcorejava.service.OrderService"%>
<%@ page import="com.training.ordmgmtprojcorejava.service.ItemService"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.List"%>
<HTML>
<HEAD>
<TITLE>Submitting a Form</TITLE>
</HEAD>

<BODY>
	<H1>Enter the search keyword</H1>
	<!-- /ord-mgmt-proj-jsp-manoj/Jsps/ -->
	<FORM NAME="form1" ACTION="/ord-mgmt-proj-jsp-manoj/Jsps/searchItem.jsp">
		Search Keyword: <input type="text" name="keyword"
			placeholder="Enter something"> 
			<INPUT TYPE="SUBMIT" VALUE="Search" name="submit">
			
	</FORM>
	
	<FORM ACTION="cartItems.jsp" METHOD="POST">

		<%
			OrderService orderService = ServiceObjectFactory.getOrderService();
			List<Item> cartItems = orderService.getCartItems(session);
			if (cartItems != null) {
				out.println("Total CartItems:" + cartItems.size());
			%>
			
			<INPUT TYPE="SUBMIT" VALUE="ShowCartItems">
			
			<%
			}
		%>

		
	</FORM>
</BODY>
</HTML>