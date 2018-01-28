<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				if (username.length() > 100){
					alert("Username is too long.  Must be under 100 characters.");
					document.existing.username.focus();
					return false;
				}
				if (password.lenght() > 50){
					alert("Password is too long.  Must be under 50 characters.");
					document.existing.password.focus();
					return false;
				}
				return true;
			}
			function validateNew(){
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
				if (username.length() > 100){
					alert("Username is too long.  Must be under 100 characters.");
					document.existing.username.focus();
					return false;
				}
				if (password.lenght() > 50){
					alert("Password is too long.  Must be under 50 characters.");
					document.existing.password.focus();
					return false;
				}
				if (firstName == ""){
					alert("First name is blank.");
					document.existing.password.focus();
					return false;
				}
				if (lastName == ""){
					alert("Last name is blank.");
					document.existing.password.focus();
					return false;
				}
				return true;
			}
			function changeForm(){
				var selectBox = document.getElementById("userType");
				var table = document.getElementById("newTable");
				var userInput = selectBox.options(selectBox.selectedIndex).value;
				alert("Working as intended.")
				/* if(userInput != null && userInput.equals("student")){
					var tr = document.createElement('tr');
					tr.innerHTML = "<td>Country of Origin</td><td><input type=\"text\" name=\"country\"></td>";
					table.appendChild(tr);
				}else{
					if(userInput != null && userInput.equals("student_family")){
						//<tr><td>Child's First Name</td><td><input type="text" name="childFirstName"></td></tr>
						//<tr><td>Child's Last Name</td><td><input type="text" name="childLastName"></td></tr>
					}else{
						//<tr><td>Phone Number</td><td><input type="text" name="phone"></td></tr>
						//<tr><td>Address</td><td><input type="text" name="address"></td></tr>
					}
				} */
			}
		</script>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<br/>
		<br/>
		<%if(userID == null){ %>
			<h3>Existing Member</h3>
			<form name="existing" method="post" action="LoginServlet">
				<table>
					<tr><td>Username</td><td><input type="text" name="username"></td></tr>
					<tr><td>Password</td><td><input type="password" name="password"></td></tr>
					<tr><td></td><td><input onClick="return validateExisting();" name="submit" type="submit" value="Login"></td></tr>
				</table>
			</form>
			<%if(request.getAttribute("errorMessage")!=null){
				out.println(request.getAttribute("errorMessage"));
			}else{
				if(request.getAttribute("userID") != null && cookies.length > 0){
					String usrID = request.getAttribute("userID").toString();
					Cookie c = new Cookie("userID",usrID);
					response.addCookie(c);
					c = new Cookie("userID",usrID);
					response.sendRedirect("index.jsp");
				}
			}%>
			<br/>
			<br/>
			<br/>
			<h3>New Members</h3>
			<form name="new" method="post" action="LoginServlet">
				<label for="userType">User Type: </label>
				<select name="userType" id="userType" onChange="return changeForm();">
					<option value="student">Student</option>
					<option value="host_family" selected="selected">Host Family</option>
					<option value="student_family">Student's Family</option>
				</select>
					<table id="newTable">
						<tr><td>First Name</td><td><input type="text" name="firstName"></td></tr>
						<tr><td>Last Name</td><td><input type="text" name="lastName"></td></tr>
						<!--  insert here -->
						<tr><td>Email</td><td><input type="text" name="username"></td></tr>
						<tr><td>Password</td><td><input type="text" name="password"></td></tr>
						<tr><td></td><td><input onClick="return validateNew();" name="submit" type="submit" value="Submit"></td></tr>
					</table>
			</form>
		<%}else{
			out.println("You are already logged in.");
		}%>
	</body>
</html>