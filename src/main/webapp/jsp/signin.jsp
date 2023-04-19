<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー 新規登録ページ</title>
</head>
<body>
<h1>ブラックジャック</h1>
<p>ユーザー 新規登録</p>
<form method="post" action="">
	名前
	<input type="text" name="name"><br>
	ニックネーム
	<input type="text" name="nickName"><br>
	パスワード
	<input type="password" name="password"><br>
	確認用パスワード
	<input type="password" name="checkPassword"><br>
	<button type=submit >新規登録</button>
</form>
<a href="login.jsp">ログイン</a>
</body>
</html>