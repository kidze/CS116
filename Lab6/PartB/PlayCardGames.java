public class PlayCardGames{
	public static void main(String[] args){
		Poker poker = new Poker();
		Bridge bridge = new Bridge();
		poker.displayDescription();
		bridge.displayDescription();
		
		Card[] pokerHand1 = poker.deal();
		Card[] pokerHand2 = poker.deal();
		
		Card[] bridgeHand1 = bridge.deal();
		Card[] bridgeHand2 = bridge.deal();
		System.out.println("=====================");
		for(Card c : pokerHand1)
			System.out.println(c.toString());
		System.out.println("=====================");
		for(Card c : pokerHand2)
			System.out.println(c.toString());
		System.out.println("=====================\n\n");
		for(Card c : bridgeHand1)
			System.out.println(c.toString());
		System.out.println("=====================");
		for(Card c : bridgeHand2)
			System.out.println(c.toString());
	}
}