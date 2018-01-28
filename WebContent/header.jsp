<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="user.Users" %>
<%
	Cookie[] cookies = request.getCookies();
	Users currentUser = new Users();
	String userID = null;
	String userType = null;
	if(cookies != null && cookies.length > 1){
		for(int i = 0; i < cookies.length; i++){
			if(cookies[i].getName().equals("userID")){
				userID = cookies[i].getValue();
			}
		}
		userType = currentUser.getUserType(userID);
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CIEE - Alaska</title>
		<link href="cssstyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>Council on International Educational Exchange - Alaska</h1>
		<div id="navbar">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="eventCalendar.jsp">Event Calendar</a></li>
				<li><a href="contactUs.jsp">Contact Us</a></li>
				<%if(userID != null && userType != null && userType.equals("admin")){%>
						<li><a href="approveUsers.jsp">Approve Users</a></li>
						<li><a href="roster.jsp">Roster</a></li>
				<%}else{
					if(userID != null){ %>
						<li><a href="myProfile.jsp">My Profile</a></li>
						<li><a href="roster.jsp">Roster</a></li>
					<%}else{%>
						<li><a href="login.jsp">Log-in</a></li>
					<%}	
				}%>
			</ul>
		</div>
	</body>
</html>