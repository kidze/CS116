public class PersonalLoan extends Loan{
	public PersonalLoan(String ln, double a, int t,double inter){
		super(ln, a, t);
		interestRate = inter + 0.02;
	}
	public String toString(){
		return "Loan type: Personal\n"+ super.toString()+"\n\nTotal amount due: "+calculateTotal();
	}
}