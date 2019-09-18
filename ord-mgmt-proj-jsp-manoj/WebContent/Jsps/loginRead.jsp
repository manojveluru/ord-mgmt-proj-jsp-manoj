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
<TITLE>Customer Page</TITLE>
</HEAD>

<BODY>

<%
OrderService orderService = ServiceObjectFactory.getOrderService();
CustomerService customerService = ServiceObjectFactory.getCustomerService();

String userName = request.getParameter("userName");
String password = request.getParameter("password");
long customerId = customerService.validateUser(userName, password);

if (customerId >= 0) {
	
	List<Item> cartItems = orderService.getCartItems(session);
	Order order = orderService.calculatePrice(cartItems);
	order.setCustomerId(customerId);	
	//Insert Order
	long orderId = orderService.insertOrder(order); //use the insertOrderStatus as needed
	
	if(orderId >= 0) {
		out.println(userName+", Your order has been placed. <BR>");
		out.println("Total price of your Order is " + order.getTotalPrice() +"<BR>");
		out.println("Order ID =" + orderId);
	}else {
		out.println("Sorry, we are unable to process the order.");
	}
	//Clear cartItems
	boolean clearCartItemsStatus = orderService.clearCartItems(); //use the clearCartItemsStatus as needed
	
} else {
	session.setAttribute("loginFailed", "Invalid UserName or Password");
%>
<jsp:forward page="login.jsp"/>"
<%
}
%>
</BODY>
</HTML>