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
<TITLE>Item Details</TITLE>
</HEAD>

<BODY>
<%
String idStr = request.getParameter("itemId");
long itemId = Long.parseLong(idStr);

ItemService itemService = ServiceObjectFactory.getItemService();
Item item = itemService.getItemByItemId(itemId);
%>

<table border='1' cellpadding='3' cellspacing='0'>
		<tr>
			<td><b>Item Id</b></td>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Item Price</b></td>
			<td><b>Item IsOnSale</b></td>
			<td><b>Item Discount</b></td>
		</tr>

		<tr>
			<td align='right'><%=item.getItemId()%></td>
			<td align='right'><%=item.getItemName()%></td>
			<td align='right'><%=item.getItemDescription()%></td>
			<td align='right'><%=item.getItemPrice()%></td>
			<td align='right'><%=item.isOnSale()%></td>
			<td align='right'><%=item.getDiscount()%></td>
		</tr>
	</table>
	
	<FORM ACTION="/ord-mgmt-proj-jsp-manoj/Jsps/AddToCart.jsp" METHOD="POST">
  Item Quantity <input type ="text" name="itemQuantity" size="25" /><br />
		<input type="hidden" name="itemId" value="<%=itemId%>" size="25" /> <br />
		<input type="submit" value="Add To Cart" />
	</FORM>
</BODY>
</HTML>