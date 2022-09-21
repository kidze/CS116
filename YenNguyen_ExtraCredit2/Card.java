package carddeck.service.classes;
/*Task 2 import neccessary user defined classes if needed*/
import carddeck.service.classes.CardValue;
public class Card {
   private CardSign sign;
   private CardValue value;
   
public Card(CardSign sign, CardValue value) {   // constructor
      this.sign = sign;
      this.value = value;
   }
   
   public CardSign getSign() { return this.sign; }
   
   public CardValue getValue() { return this.value; }
   
   public String toString() { return "This card is " + this.sign + " of " + this.value; }
   
   public int compareCards(Card aCard){//return -1 if this card object is less than the parameter aCard's, 0 if they are equal and 1 if this card object is greater than aCard's 
	  /*Task 3 Code to compare two cards*/ 
	  if(this.getSign().ordinal() > aCard.getSign().ordinal())
		  return 1;
	  else if(this.getSign().ordinal() == aCard.getSign().ordinal()){
		  if(this.getValue().ordinal() > aCard.getValue().ordinal())
			  return 1;
		  if(this.getValue().ordinal() == aCard.getValue().ordinal())
			  return 0;
		  else
			  return -1;
	  }
	  else
		  return -1;
   }
}