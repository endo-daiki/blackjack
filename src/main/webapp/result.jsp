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
<%@ page import="java.util.HashMap,java.util.Map,model.Game,gameSystem.Card,gameSystem.Hand,gameSystem.Point,gameSystem.Player,gameSystem.Result,gameSystem.Status,java.util.List" %>
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
<title>ディーラーターン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</head>
<body class="p-4">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<h1 class="text-center">ブラックジャック</h1>
			<p class="text-center">ゲームリザルト</p>
			<div class="col-12 border">
				<% 
            		Hand dealerNormalHand = dealer.getHand().get(Status.PLAYING); 
            		List<Card> dealerHandList = dealerNormalHand.getList();
            		Point dealerPoint = dealer.getPoint(Status.PLAYING);
            	%>
				<% for(Card card : dealerHandList) { %>
				<img src="img/<%= card.suit %>_<%= card.cardNumber %>.png"
					width="100" height="150">
				<% } %>
				<p class="text-center text-danger">
					<% if(dealerPoint.burstCheck()) { %>
					Burst!!
					<% } %>
					<% if(dealerPoint.bjCheck()) { %>
					BlackJack!!
					<% } %>
				</p>
				<p class="text-center">
					<%= dealerPoint.getScore() %>
				</p>
			</div>
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
				<p class="h3 text-center text-danger">
					<%= hand.getResult().getMsg() %>
				</p>
				<p class="text-center text-danger">
					<% if(point.burstCheck()) { %>
					Burst!!
					<% } %>
					<% if(point.bjCheck()) { %>
					BlackJack!!
					<% } %>
				</p>
				<p class="text-center">
					<%= score %>
				</p>
				<% if(!hand.movedCheck()) { %>
				<input class="form-check-input" type="radio" name="select"
					value="<%= key %>">
				<% } %>
			</div>
			<% } %>
			<div class="col-12 border">
				<p class="d-block text-center">
					チップ : <%= user.getTip() %> / 取得枚数　： <%= game.getBet().refund() %>
				</p>
				<form action="Setup" method="post">
					<input type="hidden" name="id" value="<%= user.getId() %>">
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
					<div class="text-center border">
						<button type="submit" class="btn btn-primary">RE START</button>					
						<a href="Main" class="btn btn-outline-primary">終了</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>