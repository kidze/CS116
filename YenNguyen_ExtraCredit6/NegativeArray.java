import java.util.Scanner;
public class NegativeArray{
	public static void main(String[] args){
		try{
			Scanner scanner = new Scanner(System.in);
			String[] array = new String[Integer.parseInt(scanner.next())];
			System.out.println("Successful");
		}catch(NumberFormatException e){
			System.out.println("Non-numerical size");
		}catch(NegativeArraySizeException e){
			System.out.println("Negative size");
		}
	}
}