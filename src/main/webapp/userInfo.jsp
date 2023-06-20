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
<title>ユーザー情報</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
<div class="container-fluid">
    <h1 class="text-center">ブラックジャック</h1>
    <p class="text-center">ユーザー情報</p>
    <div class="row justify-content-center">
        <div class="col-7">
            <table class="table">
                <tbody>
                  <tr>
                    <th scope="row">ID</th>
                    <td><%= user.getId() %></td>
                  </tr>
                  <tr>
                    <th scope="row">名前</th>
                    <td><%= user.getName() %></td>
                  </tr>
                  <tr>
                    <th scope="row">パスワード</th>
                    <td><% for(int i = 0; i < user.getPassword().length() + 5; i++) { %>*<% } %></td>
                  </tr>
                </tbody>
              </table>
              <a href="UserEdit" class="btn btn-primary">変更</a>
              <a href="UserDelete" class="btn btn-danger">退会</a>
              <a href="Main">戻る</a>
        </div>
    </div>
</div>
</body>
</html>