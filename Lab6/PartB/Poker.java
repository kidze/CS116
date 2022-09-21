public class Poker extends CardGame{
	public Poker(){
		super();
		shuffle();
		numberOfCardsDealt = 5;
	}
	public void displayDescription(){
		System.out.println("Poker is a family of gambling card games. All poker variants involve betting as an intrinsic part of play, and determine the winner of each hand according to the combinations of players' cards, at least some of which remain hidden until the end of the hand. Poker games vary in the number of cards dealt, the number of shared or \"community\" cards, the number of cards that remain hidden, and the betting procedures.");
	}
	public Card[] deal(){
		Card[] output = new Card[numberOfCardsDealt];
		for(int i = 0; i<= output.length-1;i++){
			output[i] = deck.remove(0);
		}
		return output;
	}
}