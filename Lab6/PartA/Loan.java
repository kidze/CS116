public abstract class Loan implements LoanConstants{
	//protected static int numID;
	protected int ID;
	protected static int allID;
	protected String lastName;
	protected double amount;
	protected double interestRate;
	protected int term;
	
	public Loan(String ln, double a, int t){
		allID++;
		ID = allID;
		lastName = ln;
		amount = a;
		if(t!=SHORTTERM && t!= MEDIUMTERM && t!= LONGTERM)
			term = SHORTTERM;
		else
			term = t;
		interestRate = 0;
	}
	public String toString(){
		return "===========\nLoan number: "+ID+"\nLast name: "+lastName+"\nAmount: "+amount+"\nInterest rate: "+interestRate+"\nTerm: "+term;
	}
	public double calculateTotal(){
		double totaldue = amount*(1+term*interestRate);
		return totaldue;
	}
}