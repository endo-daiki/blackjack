<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if(request.getAttribute("user_login") != null) { %>
		<p class="text-danger"><%= request.getAttribute("user_login") %></p>
	<% } %>
</body>
</html>