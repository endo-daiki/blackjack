<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.playLog, java.util.List" %>
<% 
 	User user = (User) request.getAttribute("user");
	@SuppressWarnings("unchecked")
	List<User> ranker = (List<User>) request.getAttribute("ranker");
	@SuppressWarnings("unchecked")
	List<playLog> playLog = (List<playLog>) request.getAttribute("playLog");
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
<title>戦績情報</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<h1 class="text-center">ブラックジャック</h1>
		<p class="text-center">戦績情報</p>
		<div class="row justify-content-center">
			<div class="col-7">
				<p class="text-center">
					ユーザー名：<%= user.getName() %></p>
				<table class="table">
					<tbody>
						<tr>
							<th scope="row">プレイ回数</th>
							<td><%= user.getPlaying() %></td>
						</tr>
						<tr>
							<th scope="row">手持ちチップ</th>
							<td><%= user.getTip() %></td>
						</tr>
					</tbody>
				</table>
				<p class="text-center">トップ５</p>
				<table class="table">
					<tbody>
						<% int rank = 1; %>
						<% for(int i = 0; i < ranker.size(); i++) { %>
						<tr>
							<th scope="row"><%= rank %>位</th>
							<td><%= ranker.get(i).getName() %></td>
							<td><%= ranker.get(i).getTip() %></td>
						</tr>
						<% rank++; %>
						<% } %>
					</tbody>
				</table>
				<p class="text-center">ゲーム記録</p>
				<div class="overflow-auto" style="max-height: 400px;">
					<table class="table">
						<tbody>
							<% for(int i = 0; i < playLog.size(); i++) { %>
							<tr>
								<td><%= playLog.get(i).getTime() %></td>
								<td><%= playLog.get(i).getLog() %></td>
							</tr>
							<% rank++; %>
							<% } %>
						</tbody>
					</table>
				</div>
				<p class="text-center my-4">
					<a class="btn btn-outline-primary" href="Main">戻る</a>
				</p>		
			</div>
		</div>
	</div>
</body>
</html>