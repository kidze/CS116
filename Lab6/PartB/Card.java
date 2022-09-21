public class Card {
   private String sign;
   private String value;
   
public Card(String sign, String value) {   // constructor
      this.sign = sign;
      this.value = value;
   }
   
   public String getSign() { return this.sign; }
   
   public String getValue() { return this.value; }
   
   public String toString() { return this.sign + " of " + this.value; }
   
  
}