<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
 User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
<div class="container-fluid">
    <h1 class="text-center">ブラックジャック</h1>
    <p class="text-center">戦績情報</p>
    <div class="row justify-content-center">
        <div class="col-7">
        	<p class="text-center">ユーザー名：<%= user.getName() %></p>
            <table class="table">
                <tbody>
                  <tr>
                    <th scope="row">プレイ回数</th>
                    <td><%= user.getPlaying() %></td>
                  </tr>
                  <tr>
                    <th scope="row">勝ち数</th>
                    <td><%= user.getWin() %></td>
                  </tr>
                  <tr>
                    <th scope="row">負け数</th>
                    <td><%= user.getLose() %></td>
                  </tr>
                  <tr>
                    <th scope="row">引き分け数</th>
                    <td><%= user.getDraw() %></td>
                  </tr>
                  <tr>
                    <th scope="row">勝率</th>
                    <% if(Double.isNaN(user.getRate())) {%>
                    	<td>0</td>
                    <% } else { %>
                    	<td><%= user.getRate() %></td>
                    <% } %>
                  </tr>
                </tbody>
              </table>
              <a href="main.jsp">戻る</a>
        </div>
    </div>
</div>
</body>
</html>