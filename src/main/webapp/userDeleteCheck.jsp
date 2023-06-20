<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
	User user = (User)session.getAttribute("user"); 
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
<title>ユーザー退会 確認</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">ユーザー退会 確認</p>
		<div class="row justify-content-center">
			<div class="col-7">
				<p>退会します。よろしいですか？</p>
				<p>
					ユーザー名 :
					<%= user.getName() %>様
				</p>
				<form method="post" action="UserDelete">
					<input type="hidden" value="<%= user.getId() %>" name="id">
					<button type=submit class="btn btn-danger">退会する</button>
				</form>
				<a href="UserInfo">戻る</a>
			</div>
		</div>
	</div>
</body>
</html>