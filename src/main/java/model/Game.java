package model;

import java.util.List;

import gameSystem.Deck;

import java.util.ArrayList;


public class Game {
	private Deck deck;
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerPoint;
	private int dealerPoint;
	private boolean playerBurst;
	private boolean dealerBurst;
	private String result;
	private String user_id;
	private boolean ace;
	private boolean finished;
	
	public Game() {}
	public Game(String user_id) {
		this.deck = new Deck();
		this.playerHand = new ArrayList<Card>();
		this.dealerHand = new ArrayList<Card>();
		this.playerPoint = 0;
		this.dealerPoint = 0;
		this.playerBurst = false;
		this.dealerBurst = false;
		this.user_id = user_id;
		this.ace = false;
		this.finished = false;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public List<Card> getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}
	
	public List<Card> getDealerHand() {
		return dealerHand;
	}	
	public void setDealerHand(List<Card> dealerHand) {
		this.dealerHand = dealerHand;
	}
	
	public int getPlayerPoint() {
		return playerPoint;
	}
	public void setPlayerPoint(int point) {
		this.playerPoint = point;
	}
	
	public int getDealerPoint() {
		return dealerPoint;
	}
	public void setDealerPoint(int point) {
		this.dealerPoint =  point;
	}
	
	public boolean getPlayerBurst() {
		return playerBurst;
	}
	public void setPlayerBurst(boolean burst) {
		this.playerBurst = burst;
	}
	
	public boolean getDealerBurst() {
		return dealerBurst;
	}
	public void setDealerBurst(boolean burst) {
		this.dealerBurst = burst;
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
	
	public boolean getAce() {
		return this.ace;
	}
	public void setAce(boolean ace) {
		this.ace = ace;
	}
	
	public boolean getFinished() {
		return this.finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
}
