<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<script>
	function ajaxEvent() {

    var xmlHttp;
    try // Firefox, Opera 8.0+, Safari
    {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try // Internet Explorer
        {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Your browser does not support AJAX!");
                return false;
            }
        }
    }

    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
            document.getElementById("info").innerHTML = xmlHttp.responseText;
        }
    }
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    xmlHttp.open("POST", "../ajaxservice.htm?username="+username+"&password="+password, true);
    xmlHttp.send();
}
</script>
	<title>NUBid - New day, new bids</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1>
	<img src ="https://storage-prtl-co.imgix.net/endor/organisations/11238/logos/1556893197_nu_mononu_cmyk_rb.jpg" width="100" height="100">
 NUBid </h1>
<form action="${contextPath}/v1/user/home.htm" method="POST"> 
		<table>
		<tr>
		    <td>USERNAME:</td>
		    <td><input type="text" name="username" id="username" maxlength="50" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>PASSWORD:</td>
		    <td><input type="password" name="password" id="password" maxlength="50" size="30" required="required"/></td>
		</tr>
		<tr>
			<td>ROLE:</td>
			<td>
				<select name="role" required="required">
					<option value="admin">Admin</option>
			   		<option value="user">User</option>
				</select>
			</td>		
		<tr>
		
		<tr>
		    <td colspan="2"><input type="submit" name="action" value="Login" /></td>
		</tr>
				
		</table>
	</form>
	<br><br>
	<div id="info"></div>
	<br><br><br><br>
	
	<a href="${contextPath}/v1/user/create.htm">Create New User Account</a>	

</body>
</html>