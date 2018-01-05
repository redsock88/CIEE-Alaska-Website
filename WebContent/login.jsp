<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Alaska Foreign Exchange Student Portal</title>
		<link href="cssstyle.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function validateExisting(){
				var username = document.existing.username.value;
				var password = document.existing.password.value;
				if (username == ""){
					alert("Username is blank.");
					document.existing.username.focus();
					return false;
				}
				if (password == ""){
					alert("Password is blank.");
					document.existing.password.focus();
					return false;
				}
				return true;
			}
			
		</script>
	</head>
	<body>
		<h1>Alaska Foreign Exchange Student Portal</h1>
		<div>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="eventCalendar.jsp">Event Calendar</a></li>
				<li><a href="contactUs.jsp">Contact Us</a></li>
				<li><a href="#" class='active-page'>Log-in</a></li>
			</ul>
		</div>
		<br/>
		<br/>
		<h3>Existing Member</h3>
		<form name="existing" method="post" action="LoginServlet">
			<table>
				<tr><td>Username</td><td><input type="text" name="username"></td></tr>
				<tr><td>Password</td><td><input type="password" name="password"></td></tr>
				<tr><td></td><td><input onClick="return validateExisting();" type="submit" value="login"></td></tr>
			</table>
		</form>
		<%if(request.getAttribute("errorMessage")!=null){
			out.println(request.getAttribute("errorMessage"));
		}%>
	</body>
</html>