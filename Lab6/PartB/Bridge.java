public class Bridge extends CardGame{
	public Bridge(){
		super();
		shuffle();
		numberOfCardsDealt = 13;
	}
	public void displayDescription(){
		System.out.println("Contract bridge, or simply bridge, is a trick-taking game using a standard 52-card deck. It is played by four players in two competing partnerships,[1] with partners sitting opposite each other around a table.[2] Millions of people play bridge worldwide in clubs, tournaments, online and with friends at home, making it one of the world's most popular card games, particularly among seniors.[3][4] The World Bridge Federation is the governing body for international competitive bridge.");
	}
	public Card[] deal(){
		Card[] output = new Card[numberOfCardsDealt];
		for(int i = 0; i<= output.length-1;i++){
			output[i] = deck.remove(0);
		}
		return output;
	}
}