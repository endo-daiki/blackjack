<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー 新規登録ページ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
<div class="container-fluid">
	<div class="row justify-content-center">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">ユーザー 新規登録</p>
		<div class="col-7">
			<form method="" action="">
				<label>名前</label>
				<input class="form-control" type="text" name="name"><br>
				<label>ニックネーム</label>
				<input class="form-control" type="text" name="nickname"><br>
				<label>パスワード</label>
				<input class="form-control" type="password" name="password"><br>
				<label>確認用パスワード</label>
				<input class="form-control" type="password" name="checkPassword"><br>
				<button class="btn btn-primary" type=submit >新規登録</button>
			</form>
			<a href="login.jsp">ログイン</a>
		</div>
	</div>
</div>
</body>
</html>