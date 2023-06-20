package model;

import gameSystem.Bet;
import gameSystem.Deck;
import gameSystem.Player;

public class Game {
	private Deck deck;
	private Player player;
	private Player dealer;
	private Bet bet;

	public Game() {}
	public Game(int bet) {
		this.deck = new Deck();
		this.player = new Player();
		this.dealer = new Player();
		this.bet = new Bet(bet);
	}

	public Deck getDeck() {
		return this.deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
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
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
}
