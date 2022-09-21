import java.util.Vector;
import java.util.Scanner;
public class Mnemonics{
	public static final String[] table = {"ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};
	//public Vector<String> list;
	
	public Vector<String> listMnemonics(String number){
		String num = number.replaceAll(" ","");
		num = num.replaceAll("1","");
		num = num.replaceAll("0","");
		if(num.length()==0)
			return null;
		else{
			int firstDigit = Integer.parseInt(num.substring(0,1));
			String correspondingLetters = table[firstDigit-2];
			String remain = num.substring(1);
			Vector<String> list = new Vector<String>();
			for(int i =0;i <= correspondingLetters.length()-1; i++){
				char letter = correspondingLetters.charAt(i);
				if(num.length()<=1){
					list.add(""+letter);
				}
				else{
					for(int j = 0; j <= listMnemonics(remain).size() -1; j++){
						list.add(letter + listMnemonics(remain).elementAt(j));
					}
				}
			}
			return list;
		}
	}
	
	public static void main(String[] args){
		Mnemonics client = new Mnemonics();
		Vector<String> output = new Vector<String>();
		System.out.println("Type the number");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		output = client.listMnemonics(input);
		if(output == null)
			System.out.println("No mnemonics");
		else{
			System.out.println("The mnemonics are:");
			for(int i = 0; i<= output.size()-1;i++)
				System.out.println(output.elementAt(i));
		}
	}
	
}