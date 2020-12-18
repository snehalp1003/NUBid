<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Create New User Account</h1>
	<form action="${contextPath}/v1/user/register.htm" method="POST"> 
		<table>
			<tr>
			    <td>EMAIL ADDRESS:</td>
			    <td><input type="email" name="newusername"  maxlength="50" size="30" required="required" /></td>
			</tr>			
			<tr>
			    <td>PASSWORD:</td>
			    <td><input type="password" name="newpassword"  maxlength="50" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>FIRST NAME:</td>
			    <td><input type="text" name="newfname"  maxlength="20" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>LAST NAME:</td>
			    <td><input type="text" name="newlname"  maxlength="20" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>PHONE NUMBER:</td>
			    <td><input type="text" name="newphonenum"  pattern="[0-9]{10}" size="30" required="required"/></td>
			</tr>
			<tr>
			    <td>ADDRESS:</td>
			    <td><input type="text" name="newaddress"  maxlength="50" size="30"/></td>
			</tr>
			<tr>
			    <td>COLLEGE:</td>
			    <td><input type="text" name="newcollege"  maxlength="20" size="30"/></td>
			</tr>
			<tr>
			    <td>DEPARTMENT:</td>
			    <td><input type="text" name="newdept"  maxlength="20" size="30"/></td>
			</tr>
			<tr>
				<td>ROLE:</td>
				<td>
					<select name="newrole" required="required">
						<option value="admin">Admin</option>
			    		<option value="user">User</option>
					</select>
				</td>		
			<tr>
			    <td colspan="2"><input type="submit" value="Create Account" /></td>
			</tr>					
		</table>
	</form>
	
	<a href="${contextPath}">Go to Login Page</a>
</body>
</html>