<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

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

<%


String itemId= request.getParameter("itemId");
String itemQuantity = request.getParameter("itemQuantity");

ItemService itemService = ServiceObjectFactory.getItemService();
Item item = itemService.getItemByItemId(Long.parseLong(itemId));

item.setQuantity(Integer.parseInt(itemQuantity));

OrderService orderService = ServiceObjectFactory.getOrderService();
orderService.addToCart(item, session);
%>

<jsp:forward page="home.jsp"/>