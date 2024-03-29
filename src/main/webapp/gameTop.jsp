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
<title>ブラックジャック</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<h1 class="text-center">ブラックジャック</h1>
			<p class="text-center">ゲームを始めます</p>
			<div class="col-7">
				チップ : 
				<%=user.getTip()%>
				<form action="Setup" method="post">
					<input type="hidden" name="id" value="<%=user.getId()%>">
					賭け数 <select class="form-select" name="bet">
						<option selected value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">GAME START</button>
						<a class="btn btn-outline-primary" href="Main">戻る</a>						
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
</html>