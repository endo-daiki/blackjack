package model;

import gameSystem.Bet;
import gameSystem.Deck;
import gameSystem.Player;

public class Game {
	private Deck deck;
	private Player player;
	private Player dealer;
	private Player split;
	private String user_id;
	private Bet bet;
	
	public Game() {}
	public Game(int bet) {
		this.deck = new Deck();
		this.player = new Player();
		this.dealer = new Player();
		this.split = new Player();
		this.setBet(new Bet(bet));
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public String getUserId() {
		return this.user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getDealer() {
		return dealer;
	}
	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}
	public Player getSplit() {
		return split;
	}
	public void setSplit(Player split) {
		this.split = split;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
}
