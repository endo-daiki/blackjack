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
<title>ユーザー 新規登録ページ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<h1 class="text-center">ブラックジャック</h1>
			<p class="text-center">ユーザー 新規登録</p>
			<div class="col-7">
				<%
				if (request.getAttribute("error_msg") != null) {
				%>
				<p class="text-danger">
					登録エラー：<%=(String) request.getAttribute("error_msg")%></p>
				<%
				}
				%>
				<form method="post" action="Signup">
					<label>ID</label> <input class="form-control" type="text" name="id"
						required><br> <label>名前</label> <input
						class="form-control" type="text" name="name" required><br>
					<label>パスワード</label> <input class="form-control" type="password"
						name="password" pattern="^[a-zA-Z0-9\d]{8,}$"
						placeholder="8文字以上で入力してください" required><br> <label>確認用パスワード</label>
					<input class="form-control" type="password" name="checkPassword"
						pattern="^[a-zA-Z0-9\d]{8,}$" placeholder="上記と同じパスワードを入力してください"
						required><br>
						<div class="text-center">
							<button class="btn btn-primary" type=submit>新規登録</button>
							<p class="my-2">
								<a href="login.jsp" class="btn btn-outline-primary">ログイン</a>
							</p>
						</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>