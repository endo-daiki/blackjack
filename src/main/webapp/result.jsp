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
<%@ page import="model.Game,gameSystem.Card,gameSystem.Hand,gameSystem.Point,gameSystem.Player,java.util.List" %>
<% 
	Game game = (Game) request.getAttribute("game");
	Player player = game.getPlayer();
	Player split = game.getSplit();
	Player dealer = game.getDealer();
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
            <div class="col-12 border">
           		<% 
            		Hand dealerHand = dealer.getHand(); 
            		List<Card> dealerHandList = dealerHand.getList();
            		Point dealerPoint = dealer.getPoint();
            	%>
            	<% for(Card card : dealerHandList) { %>
            		<img  src="img/<%= card.getSuit() %>_<%= card.getCardNumber().getNo() %>.png" width="100" height="150">
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
            <% if(!(split.result.equals("ready"))) { %>
            	<% 
	            	Hand splitHand = split.getHand(); 
	            	Point splitPoint = split.getPoint(); 
	            	List<Card> splitHandList = splitHand.getList();
	            	int point = splitPoint.getScore();
	            %>
	            <div class="col-6 border <% if(split.result.equals("playing")) { %>border-danger<%}%>">
	            	<% for(Card card : splitHandList) {  %>
	            		<img src="img/<%= card.getSuit() %>_<%= card.getCardNumber().getNo() %>.png" width="100" height="150">
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
	             		<% if(splitPoint.aceCountCheck() && split.result.equals("playing")) { %>
						<%= (point - 10) %>
						 / 
						<% } %>             		
	             		<%= point %>
	             	</p>
	             	<h3 class="text-center text-danger">
						<%= split.result %>
            		</h3>
	            </div>
            <% } %>
            <div class="col-6 border">
            	<% 
	            	Hand playerHand = player.getHand(); 
	            	Point playerPoint = player.getPoint(); 
	            	List<Card> playerHandList = playerHand.getList();
	            	int point = playerPoint.getScore();
	            %>
            	<% for(Card card : playerHandList) { %>
            		<img  src="img/<%= card.getSuit() %>_<%= card.getCardNumber().getNo() %>.png" width="100" height="150">
            	<% } %>
            	 <p class="text-center text-danger">
		             	<% if(playerPoint.burstCheck()) { %>
							Burst!!
						<% } %>
						<% if(playerPoint.bjCheck()) { %>
	          				BlackJack!!
	          			<% } %>
            		</p>
              	<p class="text-center">
              		<%= point %>
              	</p>
              	<h3 class="text-center text-danger">
              		<%= player.result %>
              	</h3>
            </div>
            <p class="col-12">refund is <%= game.getBet().refund() %></p>
            <div class="col-12 border">
            	<form action="Setup" method="post">
            		bet is
				 <select class="form-select" name="bet">
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
				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-outline-primary">RE START</button>
				</div>
				</form>
            	<a href="Main" class="btn btn-danger">終了</a>
        </div>
    </div>

</body>
</html>