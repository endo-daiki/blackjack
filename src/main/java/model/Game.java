package model;

import java.util.List;

public class Game {
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private int playerPoint;
	private int dealerPoint;
	private boolean playerBurst;
	private boolean dealerBurst;
	private String result;
	
	
	public List getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}
	
	public List getDealerHand() {
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
}