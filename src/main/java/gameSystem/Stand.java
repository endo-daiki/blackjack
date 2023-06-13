package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game, String select) {
		if(select == null) {
			return "PlayerTurn";
		}
		
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();

		
//		player.isStand(key);
//		if(player.moveCheckAll()) { //手札mapすべてのステータスが動けるものかどうか確認
//			return "PlayerTurn";
//		}		
//		if(player.burstCheckAll()) { //手札mapすべてで、バーストしていないかどうか
//			while(dealer.getPoint().getScore() < 17) { //dealerは手札は一個しかないので、overrideや別クラスで考えた方がいい?
//				dealer.draw(deck);
//			}
//		}
//		if(select.equals("split")) {
//			player.setStatus(Status.SPLIT);
//		} else {
//			player.setStatus(Status.PLAYING);
//		}
//		player.judge(dealer);
//		game.setDeck(deck);
//		game.setPlayer(player);
//		game.setDealer(dealer);
		
		
		player.isStand(); //今使っている手札のステータスをスタンドにする
		
		if(!player.moveCheck()) { //手札Mapのステータスすべてがスタンドかどうか確認する
			return "PlayerTurn";
		}

		if(!player.getPoint().burstCheck() || !player.getSplitPoint().burstCheck() && player.getSplitHand().sizeCheck() > 0) {
			while(dealer.getPoint().getScore() < 17) {
				dealer.draw(deck);
			}
		}

		player.judge(dealer);
		
		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		return "Result";
	}
}
