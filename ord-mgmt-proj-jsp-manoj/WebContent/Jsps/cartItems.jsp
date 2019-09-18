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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

OrderService orderService = ServiceObjectFactory.getOrderService();
List<Item> cartItems = orderService.getCartItems(session);

if (cartItems != null) {
%>
<table border='1' cellpadding='3' cellspacing='0'>
		<tr>
			<td><b>Item Id</b></td>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Item Price</b></td>
			<td><b>Item IsOnSale</b></td>
			<td><b>Item Discount</b></td>
			<td><b>Quantity</b></td>
		</tr>
		<%
for(Item item : cartItems){
	%>
		<tr>
			<td align='right'><%=item.getItemId()%></td>
			<td align='right'><%=item.getItemName()%></td>
			<td align='right'><%=item.getItemDescription()%></td>
			<td align='right'><%=item.getItemPrice()%></td>
			<td align='right'><%=item.isOnSale()%></td>
			<td align='right'><%=item.getDiscount()%></td>
			<td align='right'><%=item.getQuantity()%></td>
		</tr>


<%}%>
	</table>
	<%}%>
	<FORM ACTION="login.jsp" METHOD="POST">



		<INPUT TYPE="SUBMIT" VALUE="ProceedToCheckout">
	</FORM>

</body>
</html>