package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game, String key) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		Bet bet = game.getBet();
		Status status = Status.valueOf(key);

		player.isStand(status); //スタンドにする手札を決める

		if(!player.movedCheckAll()) { //すべての手札が行動可能ならプレイヤーターンに戻る
			return "PlayerTurn";
		}		
		if(!player.burstCheckAll()) {	//すべてバーストならプレイヤーの自動敗北、ディーラーはカードを引かない
			while(dealer.getPoint(Status.PLAYING).getScore() < 17) {
				dealer.draw(deck.pull(), Status.PLAYING);
			}
		}	
		bet.judge(player, dealer); //勝敗判定

		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);
		game.setBet(bet);

		return "Result"; //Resultサーブレットにリダイレクト(リロード対策)
	}
}
