import java.util.Vector;
public abstract class CardGame{
	protected Vector<Card> deck;
	protected int numberOfCardsDealt;
	public CardGame(){
		String[] sign = {"Spade","Club","Diamond","Heart"};
		String[] value = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
		deck = new Vector<Card>();
		
		for(int i = 0; i<= sign.length-1; i++){
			for(int j = 0; j<= value.length-1; j++){
				deck.add(new Card(sign[i], value[j]));
			}
		}
		numberOfCardsDealt = 0;
	}
	public void shuffle(){
		for(int i = 0; i<= deck.size()-1; i++){
			int randomPosition = 0 + (int)(Math.random()*51);
			Card card = deck.elementAt(i);
			deck.set(i, deck.elementAt(randomPosition));
			deck.set(randomPosition, card);
		}
	}
	public abstract void displayDescription();
	public abstract Card[] deal();
}