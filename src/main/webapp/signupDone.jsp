<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
	User user = (User) session.getAttribute("user"); 
	if(user != null) {
%>
<jsp:forward page="/main.jsp" />
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 完了</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">ユーザー登録完了</p>
		<div class="row justify-content-center">
			<div class="col-7">
				<p>登録を完了しました。</p>
				<p>引き続き、ログインをしてください。</p>
				<a class="btn btn-outline-primary" href="login.jsp">ログインする</a>
			</div>
		</div>
	</div>
</body>
</html>