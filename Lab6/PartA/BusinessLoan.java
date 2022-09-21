public class BusinessLoan extends Loan{
	public BusinessLoan( String ln, double a, int t, double interestr){
		super(ln, a, t);
		interestRate = interestr + 0.01;
	}
	public String toString(){
		return "Loan type: Business\n"+ super.toString()+"\n\nTotal amount due: "+calculateTotal();
	}
}