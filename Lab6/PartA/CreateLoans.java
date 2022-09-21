import java.util.Scanner;
public class CreateLoans implements LoanConstants{
	public static double primeInterestRate;
	public static int numOfLoans;
	public static Loan[] loanArray;
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter prime interest rate:");
		primeInterestRate = Double.parseDouble(scanner.nextLine());
		loanArray = new Loan[5];
		numOfLoans = 0;
		while(numOfLoans<5){
			System.out.println("Enter type of loan (P for Personal or B for Business)");
			String loanType = "";
			boolean validType = false;
			while(!validType){
				String loanTypeInput = scanner.nextLine();
				if(!loanTypeInput.equals("P") && !loanTypeInput.equals("B")){
					System.out.println("Invalid input, try again, P for Personal or B for Business");
					validType = false;
				}
				else{
					loanType = loanTypeInput;
					validType = true;
				}
			}
			System.out.println("Enter last name:");
			String lastName = scanner.nextLine();
			System.out.println("Enter amount:");
			double amount = 0.0;
			boolean validAmount = false;
			while(!validAmount){
				double amountInput = Double.parseDouble(scanner.nextLine());
				if(amountInput > MAXLOAN){
					System.out.println("Loan cannot exceed  "+MAXLOAN+". Please re-enter amount: ");
					validAmount = false;
				}
				else{
					amount = amountInput;
					validAmount = true;
				}
			}
			System.out.println("Enter term:");
			int term = Integer.parseInt(scanner.nextLine());
			
			if(loanType.equals("P")){
				loanArray[numOfLoans] = new PersonalLoan(lastName, amount, term, primeInterestRate);
				numOfLoans++;
			}
			else{
				loanArray[numOfLoans] = new BusinessLoan(lastName, amount, term, primeInterestRate);
				numOfLoans++;
			}
		}
		
		for(Loan loan : loanArray){
			System.out.println(loan.toString());
		}
	}
}