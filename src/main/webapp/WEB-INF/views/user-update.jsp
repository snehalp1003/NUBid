<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User Details</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Update User Details</h1>
	<form action="${contextPath}/v1/user/update.htm" method="POST"> 
		<table>
			<tr>
			    <td>EMAIL ADDRESS:</td>
			    <td><input type="email" name="updateusername" value="${sessionScope.currentuser.userEmailAddress}" maxlength="50" size="30" disabled /></td>
			</tr>			
			<tr>
			    <td>PASSWORD:</td>
			    <td><input type="password" name="updatepassword" value="${sessionScope.currentuser.userPassword}" maxlength="50" size="30" disabled/></td>
			</tr>
			<tr>
			    <td>FIRST NAME:</td>
			    <td><input type="text" name="updatefname" value="${sessionScope.currentuser.userFirstName}" maxlength="20" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>LAST NAME:</td>
			    <td><input type="text" name="updatelname" value="${sessionScope.currentuser.userLastName}" maxlength="20" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>PHONE NUMBER:</td>
			    <td><input type="text" name="updatephonenum" value="${sessionScope.currentuser.userPhoneNum}" pattern="[0-9]{10}" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>ADDRESS:</td>
			    <td><input type="text" name="updateaddress" value="${sessionScope.currentuser.userAddress}" maxlength="50" size="30"/></td>
			</tr>
			<tr>
			    <td>COLLEGE:</td>
			    <td><input type="text" name="updatecollege" value="${sessionScope.currentuser.userCollege}" maxlength="20" size="30"/></td>
			</tr>
			<tr>
			    <td>DEPARTMENT:</td>
			    <td><input type="text" name="updatedept" value="${sessionScope.currentuser.userDept}" maxlength="20" size="30"/></td>
			</tr>			
			<tr>
			    <td colspan="2"><input type="submit" value="Update Details" /></td>
			</tr>					
		</table>
	</form>
	
<br><br>
<%@ include file="user-menu.jsp" %>
</body>
</html>