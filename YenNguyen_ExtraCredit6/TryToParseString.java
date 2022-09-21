public class TryToParseString{
	public static void main(String[] args){
		try{
		int a = Integer.parseInt("asdf");
		}
		catch(NumberFormatException e){
			System.out.println("cannot parse");
		}
	}
}