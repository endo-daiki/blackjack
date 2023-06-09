<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
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
<title>ユーザー　ログインページ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
<div class="container-fluid">
	<div class="row justify-content-center">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">ユーザー ログイン</p>
		<div class="col-7">
			<% if(request.getAttribute("error_msg")!= null){ %>
				<p class="text-danger">ログインエラー：<%= (String)request.getAttribute("error_msg") %></p>
			<% } %>
			<form method="post" action="Login">
				<label>ID</label>
				<input type="text" name="id" class="form-control" required><br>
				<label>パスワード</label>
				<input type="password" name="password" class="form-control" required><br>
				<button type=submit class="btn btn-primary">ログイン</button>
			</form>
			<a href="signup.jsp">新規登録</a>
		</div>
	</div>
</div>
</body>
</html>