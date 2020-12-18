<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Add New Product </h1>
	<form action="${contextPath}/v1/product/add.htm" method="POST"> 
		<table>
			<tr>
			    <td>PRODUCT NAME:</td>
			    <td><input type="text" name="newprodname"  maxlength="50" size="30" required="required" /></td>
			</tr>			
			<tr>
			    <td>CATEGORY:</td>
			    <td>
			    <select name="newcategory" required="required">
			    	<option value="electronic">Electronics</option>
			    	<option value="furniture">Furniture</option>
			    	<option value="books">Books</option>
			    	<option value="clothing">Clothing</option>
			    	<option value="homedecor">Home Decor</option>
			    	<option value="other">Other</option>	
			    </select>
			    </td>
			</tr>
			<tr>
			    <td>DESCRIPTION:</td>
			    <td><input type="text" name="newdescription"  maxlength="50" size="30"/></td>
			</tr>
			<tr>
			    <td>MINIMUM BID PRICE ($):</td>
			    <td><input type="number" name="newminbidprice" min="1" step="0.01" size="30" required="required"/></td>
			</tr>			
			<tr>
			    <td colspan="2"><input type="submit" value="Add Product" /></td>
			</tr>					
		</table>
	</form>
	
	<br><br>
	<%@ include file="user-menu.jsp" %>
</body>
</html>