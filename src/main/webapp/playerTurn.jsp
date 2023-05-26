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
<%@ page import="model.Game,gameSystem.Card,gameSystem.Hand,gameSystem.Point,gameSystem.Player,java.util.List" %>
<% 
	Game game = (Game) request.getAttribute("game");
	Player player = game.getPlayer();
	Hand playerHand = player.getHand();
	Point playerPoint = player.getPoint();
	Player dealer = game.getDealer();
	Hand dealerHand = dealer.getHand();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プレイヤーターン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
    <div class="container-fluid">
        
    <div class="row justify-content-center">
            <h1 class="text-center">ブラックジャック</h1>
            <p class="text-center">プレイヤーターン</p>
            <div class="col-7 border">
            	<img src="img/<%= dealerHand.getList().get(0).getSuit() %>_<%= dealerHand.getList().get(0).getNo().getNo() %>.png" width="100" height="150">
            	<img src="img/trump_back.png" width="100" height="150">
            	<% if( dealerHand.getList().get(0).courtCheck() ) { %>
            	<p class="text-center"><%= dealerHand.getList().get(0).getNo().getNo() %>(10) + ?</p>
            	<% } else { %>
            	<p class="text-center"><%= dealerHand.getList().get(0).getNo().getNo() %> + ?</p>
            	<% } %>
            </div>
            <div class="col-7 border">
            	<% for(Card card : playerHand.getList()) {  %>
            		<img src="img/<%= card.getSuit() %>_<%= card.getNo().getNo() %>.png" width="100" height="150">
            	<% } %>
             	<p class="text-center">
             		<% if(playerPoint.aceCountCheck()) { %>
					<%= (playerPoint.getScore() - 10) %>
					 / 
					<% } %>             		
             		<%= playerPoint.getScore() %>
             	</p>
            </div>
            <div class="col-7 border">
				<form action="Hit" method="post">
					<button type="submit" class="btn btn-outline-primary">hit</button>
				</form>
				<form action="Stand" method="post">
					<button type="submit" class="btn btn-outline-danger">stand</button>
				</form>
            	<a href="gameTop.jsp" class="btn btn-outline-danger">戻る</a>
            </div>
        </div>
       </div>
</body>
</html>