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
<%@ page import="model.Game,gameSystem.Card,gameSystem.Hand,gameSystem.Point,gameSystem.Player,gameSystem.Result,java.util.List" %>
<% 
	Game game = (Game) request.getAttribute("game");
	Player player = game.getPlayer();
	Player dealer = game.getDealer();
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
            <p>my tip <%= user.getTip() %></p>
            <div class="col-12 border">    
            <% 
           		Hand dealerHand = dealer.getHand(); 
           		List<Card> list = dealerHand.getList();
           		Card openCard = list.get(0); 
            %>
           	<img src="img/<%= openCard.suit %>_<%= openCard.cardNumber.getNo() %>.png" width="100" height="150">
           	<img src="img/trump_back.png" width="100" height="150">
           	<% if( dealerHand.getList().get(0).courtCheck() ) { %>
           	<p class="text-center"><%= openCard.cardNumber.getNo() %>(10) + ?</p>
           	<% } else { %>
           	<p class="text-center"><%= openCard.cardNumber.getNo() %> + ?</p>
           	<% } %>
            </div>
            <% if(player.getResult() == Result.SPLIT) { %>
            	<% 
	            	Hand splitHand = player.getSplitHand(); 
	            	Point splitPoint = player.getSplitPoint(); 
	            	List<Card> splitHandList = splitHand.getList();
	            	int point = splitPoint.getScore();
	            %>
	            <div class="col-6 border <% if(player.getResult() == Result.SPLIT) { %>border-danger<%}%>">
	            	<% for(Card card : splitHandList) {  %>
	            		<img src="img/<%= card.suit %>_<%= card.cardNumber.getNo() %>.png" width="100" height="150">
	            	<% } %>
	             	<p class="text-center text-danger">
						<% if(splitPoint.burstCheck()) { %>
						Burst!!
						<% } %>
						<% if(splitPoint.bjCheck()) { %>
            			BlackJack!!
            			<% } %>
            		</p>
	             	<p class="text-center">
	             		<% if(splitPoint.aceCountCheck() && player.getResult().equals(Result.SPLIT)) { %>
						<%= (point - 10) %>
						 / 
						<% } %>             		
	             		<%= point %>
	             	</p>
	            </div>
            <% } %>
            <div class="col-6 border <% if(player.getResult() == Result.PLAYING) { %>border-danger<%}%>">
            	<% 
	            	Hand playerHand = player.getHand(); 
	            	Point playerPoint = player.getPoint(); 
	            	List<Card> playerHandList = playerHand.getList();
	            	int point = playerPoint.getScore();
	            %>
            	<% for(Card card : playerHandList) {  %>
            		<img src="img/<%= card.suit %>_<%= card.cardNumber.getNo() %>.png" width="100" height="150">
            	<% } %>
             	<p class="text-center text-danger">
					<% if(playerPoint.bjCheck()) { %>
          			BlackJack!!
          			<% } %>
            	</p>
             	<p class="text-center">
             		<% if(playerPoint.aceCountCheck() && player.getResult().equals("playing")) { %>
					<%= (point - 10) %>
					 / 
					<% } %>             		
             		<%= point %>
             	</p>
            </div>
            <p class="col-12">bet is <%= game.getBet().getTip() %></p>         
            <div class="col-12 border row justify-content-around">
				<form action="Hit" method="post" class="col-4">
					<div class="d-grid gap-2">
						<button type="submit" class="btn btn-primary">hit</button>					
					</div>
				</form>
				<form action="Stand" method="post" class="col-4">
					<div class="d-grid gap-2">
						<button type="submit" class="btn btn-danger">stand</button>
					</div>
				</form>
				<% if(playerHand.splitCheck()) { %>
				<form action="Split" method="post" class="col-4">
					<div class="d-grid gap-2">
						<button type="submit" class="btn btn-light">split</button>
					</div>
				</form>
				<% } %>
            	<a href="gameTop.jsp" class="btn btn-outline-danger">戻る</a>
            </div>
        </div>
       </div>
</body>
</html>