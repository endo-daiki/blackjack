<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
	User user = (User) session.getAttribute("user"); 
	if(user == null) {
%>
<jsp:forward page="/" />
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">トップぺージ</p>
		<% if(request.getAttribute("user_login") != null) { %>
		<p class="text-danger text-center"><%= request.getAttribute("user_login") %></p>
		<% } %>
		<p class="text-center">
			ようこそ、<%= user.getName()  %>さん
		</p>
		<div class="row justify-content-center">
			<div class="col-7">
				<div class="text-center">
					<a class="btn btn-primary d-block" href="gameTop.jsp">BLACKJACK</a>
					<p class="m-2">
						<a class="btn btn-outline-primary" href="UserInfo">ユーザー情報</a>					
						<a class="btn btn-outline-primary" href="PlayInfo">戦績情報</a>					
						<a class="btn btn-outline-primary" href="Logout">ログアウト</a>					
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
