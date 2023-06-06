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
<title>ユーザー情報変更</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
<div class="container-fluid">
    <h1 class="text-center">ブラックジャック</h1>
    <p class="text-center">ユーザー情報変更</p>
    <div class="row justify-content-center">
        <div class="col-7">
			<% if(request.getAttribute("error_msg") != null) { %>
			<p class="text-danger">編集エラー：<%= request.getAttribute("error_msg") %></p>
			<% } %>
            <form method="post" action="UserEdit">
				<label>ID</label>
				<input class="form-control" type="text" name="newId" value="<%= user.getId() %>" required><br>
				<label>名前</label>
				<input class="form-control" type="text" name="name" value="<%= user.getName() %>" required><br>
				<label>パスワード</label>
				<input class="form-control" type="password" name="password" value="<%= user.getPassword() %>" pattern="^[a-zA-Z0-9\d]{8,}$" placeholder="8文字以上で入力してください" required><br>
				<label>確認用パスワード</label>
				<input class="form-control" type="password" name="checkPassword" pattern="^[a-zA-Z0-9\d]{8,}$" placeholder="上記と同じパスワードを入力してください" required><br>
				<input type="hidden" name="oldId" value="<%= user.getId() %>">
				<button class="btn btn-primary" type=submit >変更</button>
			</form>
			<a href="UserInfo">戻る</a>
        </div>
    </div>
</div>
</body>
</html>