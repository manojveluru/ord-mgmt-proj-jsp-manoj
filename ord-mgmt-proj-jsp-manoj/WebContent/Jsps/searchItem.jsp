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
<TITLE>Search Item Page</TITLE>
</HEAD>

<BODY>
	<jsp:include page="home.jsp" />
	<table name="Items" border='1' cellpadding='3' cellspacing='0'>
		<tr>
			<td><b>ITEM ID</b></td>
			<td><b>ITEM NAME</b></td>
			<td><b>ITEM DESC</b></td>
			<td><b>ITEM PRICE</b></td>
			<td><b>IS ON SALE</b></td>
			<td><b>ITEM DISCOUNT</b></td>
		</tr>
		<%
			ItemService itemService = ServiceObjectFactory.getItemService();
			OrderService orderService = ServiceObjectFactory.getOrderService();
			CustomerService customerService = ServiceObjectFactory.getCustomerService();
			String keyword = request.getParameter("keyword");
			List<Item> itemList = itemService.searchItemsByKeyword(keyword);

			for (Item item : itemList) {
		%>
		<tr>
			<td><%out.println(item.getItemId()); %></td>
			<td><a href="/ord-mgmt-proj-jsp-manoj/Jsps/showItem.jsp?itemId=<%=item.getItemId()%>"><%out.println(item.getItemName()); %></a></td>
			<td><%out.println(item.getItemDescription()); %></td>
			<td><%out.println(item.getItemPrice()); %></td>
			<td><%out.println(item.isOnSale()); %></td>
			<td><%out.println(item.getDiscount()); %></td>
		</tr>

		<%
			}
		%>
	</table>
	
	
</BODY>
</HTML>