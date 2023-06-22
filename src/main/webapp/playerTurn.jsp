<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<% 
	User user = (User) session.getAttribute("user"); 
	if(user == null) {
%>
<jsp:forward page="/" />
<%
	}
%>
<%@ page
	import="java.util.HashMap,java.util.Map,model.Game,gameSystem.Card,gameSystem.Hand,gameSystem.Point,gameSystem.Player,gameSystem.Result,gameSystem.Status,java.util.List"%>
<% 
	Game game = (Game) request.getAttribute("game");
	Player player = game.getPlayer();
	Player dealer = game.getDealer();
	
	Map<Status, Hand> playerHand = player.getHand();
	Map<Status, Hand> dealerHand = dealer.getHand();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プレイヤーターン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<h1 class="text-center">ブラックジャック</h1>
				<p class="text-center">プレイヤーターン</p>
				<div class="col-12 border">
					<% 
	           		Hand dealerNormalHand = dealerHand.get(Status.PLAYING); 
	           		List<Card> list = dealerNormalHand.getList();
	           		Card openCard = list.get(0); 
	            %>
					<img
						src="img/<%= openCard.suit %>_<%= openCard.cardNumber %>.png"
						width="100" height="150"> <img src="img/trump_back.jpg"
						width="100" height="150">
					<% if( dealerNormalHand.getList().get(0).courtCheck()) { %>
					<p class="text-center"><%= openCard.cardNumber %>(10) +
						?
					</p>
					<% } else { %>
					<p class="text-center"><%= openCard.cardNumber.getPoint() %>
						+ ?
					</p>
					<% } %>
				</div>
			<form method="post">
				<% for(Status key : playerHand.keySet()) { %>
				<div class="col border">
					<% 
	            		Hand hand = playerHand.get(key);
		            	Point point = hand.getPoint(); 
		            	List<Card> handList = hand.getList();
		            	int score = point.getScore();
		            %>
					<% for(Card card : handList) {  %>
					<img src="img/<%= card.suit %>_<%= card.cardNumber %>.png"
						width="100" height="150">
					<% } %>
					<p class="text-center text-danger">
						<% if(point.burstCheck()) { %>
						Burst!!
						<% } %>
						<% if(point.bjCheck()) { %>
						BlackJack!!
						<% } %>
					</p>
					<p class="text-center">
						<% if(point.aceCountCheck() && !hand.movedCheck()) { %>
						<%= (score - 10) %>
						/
						<% } %>
						<%= score %>
					</p>
					<% if(!hand.movedCheck()) { %>
					<input class="form-check-input" type="radio" name="select"
						value="<%= key %>" checked>
					<% } %>
				</div>
				<% } %>
					<div class="text-center border">
						<p class="d-block text-center">
							チップ : <%= user.getTip() %> / 賭け数 : <%= game.getBet().getTip() %>
						</p>
						<button formaction="Hit" type="submit" class="btn btn-primary">hit</button>
						<button formaction="Stand" type="submit" class="btn btn-primary">stand</button>
						<% if(player.splitCheck()) { %>
							<button formaction="Split" type="submit" class="btn btn-primary">split</button>
						<% } %>
						<a href="gameTop.jsp" class="btn btn-outline-primary">戻る</a>					
					</div>
				</form>
				</div>
			</div>
</body>
</html>