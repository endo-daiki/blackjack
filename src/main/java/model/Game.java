package model;

import gameSystem.Deck;
import gameSystem.Hand;
import gameSystem.Point;

public class Game {
	private Deck deck;
	private Hand playerHand;
	private Hand dealerHand;
	private Point playerPoint;
	private Point dealerPoint;
	private String result;
	private String user_id;
	
	public Game() {}
	public Game(String user_id) {
		this.deck = new Deck();
		this.playerHand = new Hand();
		this.dealerHand = new Hand();
		this.playerPoint = new Point();
		this.dealerPoint = new Point();
		this.result = "playing";
		this.user_id = user_id;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Hand getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}
	public Hand getDealerHand() {
		return dealerHand;
	}	
	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}
	public Point getPlayerPoint() {
		return playerPoint;
	}
	public void setPlayerPoint(Point point) {
		this.playerPoint = point;
	}	
	public Point getDealerPoint() {
		return dealerPoint;
	}
	public void setDealerPoint(Point point) {
		this.dealerPoint =  point;
	}	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getUserId() {
		return this.user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
}
