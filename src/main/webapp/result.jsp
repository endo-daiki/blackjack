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
	
	Map<String, Hand> playerHand = player.getHand();
	Map<String, Hand> dealerHand = dealer.getHand();
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
            <p>my tip <%= user.getTip() %></p>
            <div class="col-12 border">
           		<% 
            		Hand dealerNormalHand = dealer.getHand().get("normal"); 
            		List<Card> dealerHandList = dealerNormalHand.getList();
            		Point dealerPoint = dealer.getPoint("normal");
            	%>
            	<% for(Card card : dealerHandList) { %>
            		<img  src="img/<%= card.suit %>_<%= card.cardNumber.getNo() %>.png" width="100" height="150">
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
			<% for(String key : playerHand.keySet()) { %>  
                <div class="col-6 border">
	            	<% 
	            		Hand hand = playerHand.get(key);
		            	Point testPoint = hand.getPoint(); 
		            	List<Card> handList = hand.getList();
		            	int score = testPoint.getScore();
		            %>
	            	<% for(Card card : handList) {  %>
	            		<img src="img/<%= card.suit %>_<%= card.cardNumber.getNo() %>.png" width="100" height="150">
	            	<% } %>
	             	<p class="text-center text-danger">
	             		<% if(testPoint.burstCheck()) { %>
							Burst!!
						<% } %>
						<% if(testPoint.bjCheck()) { %>
	          				BlackJack!!
	          			<% } %>
	            	</p>
	             	<p class="text-center">           		
	             		<%= score %>
	             	</p>
	             	<% if(!hand.movedCheck()) { %>
	             		<input class="form-check-input" type="radio" name="select" value="<%= key %>">           	
	             	<% } %>
	            </div>
            <% } %>    
            <p class="col-12">refund is <%= game.getBet().refund() %></p>
            <div class="col-12 border">
            	<form action="Setup" method="post">
            	<input type="hidden" name="id" value="<%= user.getId() %>">
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
    </div>

</body>
</html>