package Client.Services.Classes;
import Client.Services.BillTypes;	
public class MyBills{
	private String month;
	private BillTypes billType;
	private double[] expenses;
	private int days;
	private static int total;
	private int id;
	
	public MyBills(){
		month = "any month";
		billType = null;
		expenses = null;
		days = 0;
		total++;
		id = total;
	}
	
	public MyBills(String m, BillTypes b, double[] e, int d){
		month = m;
		billType = b;
		expenses = e;
		days = d;
		total++;
		id = total;
	}
	
	public BillTypes getBillTypes(){return billType;}
	public int getDays(){return days;}
	public double[] getExpenses(){return expenses;}
	public String getMonth(){return month;}
	
	public boolean equals(MyBills mybill){
		if(this.getBillTypes()== mybill.getBillTypes() & this.getDays()==mybill.getDays())
			return true;
		else
			return false;
	}
	
	public String toString(){
		String numbers = "";
		for(int i = 0; i<=expenses.length-1; i++)
			numbers = numbers +"  "+ expenses[i];
		return "Month: "+month+". Bill type: "+billType+". Expenses:"+numbers+". Number of days: "+days+". Total bills: "+total+". This bill ID: "+id;
	}
}