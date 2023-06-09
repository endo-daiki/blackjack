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
	Point dealerPoint = dealer.getPoint();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ディーラーターン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="p-4">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <h1 class="text-center">ブラックジャック</h1>
            <p class="text-center">ゲームリザルト</p>
            <div class="col-7 border">
            	<% for(Card card : dealerHand.getList()) { %>
            		<img  src="img/<%= card.getSuit() %>_<%= card.getCardNumber().getNo() %>.png" width="100" height="150">
            	<% } %>
               <p class="text-center">
			   	<%= dealerPoint.getScore() %>
			   </p>
            </div>
            <div class="col-7 border">
            	<% for(Card card : playerHand.getList()) { %>
            		<img  src="img/<%= card.getSuit() %>_<%= card.getCardNumber().getNo() %>.png" width="100" height="150">
            	<% } %>
            	<% if(playerPoint.getScore() == 21) { %>
            		<p class="text-center text-danger">BlackJack!!</p>
            	<% } %>
              	<p class="text-center"><%= playerPoint.getScore() %></p>
            </div>
            <h3 class="text-center text-danger">
            	<% switch(game.getResult()) { 
            		case  "win" : %>
            			you win!
            	<% 		break; 
            		case "lose" : %>
            			you lose...
            	<%      break;
            		case "draw" : %>
            			draw..
            	<% 		break; 
            	} %>
            </h3>
            <div class="col-7 border">
            	<form action="Setup" method="post">
            		<input type="hidden" name="user_id" value="<%= user.getId() %>">
					<button type="submit" class="btn btn-primary">もう一度</button>
				</form>
            	<a href="Main" class="btn btn-danger">終了</a>
            </div>
        </div>
    </div>

</body>
</html>