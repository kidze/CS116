package Client;
import Client.Services.Classes.MyBills;
import Client.Services.BillTypes;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.NumberFormat;
public class MyBillsClient
{
	public static void main(String[] args)
	{
		MyBills[] billArray = null;
		try{
			File file = new File("myexpenses.txt");
			Scanner scanForSize = new Scanner(file);
			Scanner scanner = new Scanner(file);
			int size = 0;
			while(scanForSize.hasNextLine()){
				scanForSize.nextLine();
				size++;
			}
			
			billArray = new MyBills[size/2];
			
			int index = 0;
			while(scanner.hasNextLine()){
				StringTokenizer s1 = new StringTokenizer(scanner.nextLine(),":");
				StringTokenizer s2 = new StringTokenizer(scanner.nextLine(),",");
				String a = s1.nextToken();
				String month = s1.nextToken();
				String b = s1.nextToken();
				BillTypes billtype = BillTypes.valueOf(s1.nextToken());
				double[] expenses = new double[s2.countTokens()];
				for(int i = 0; i <= expenses.length-1; i++){
					expenses[i] = Double.parseDouble(s2.nextToken());
				}
				billArray[index] = new MyBills(month, billtype, expenses, expenses.length);
				index++;
				
			}
		}
		catch(IOException e){
			System.out.println("Error");
		}
		//output 1
		for(int i = 0; i <= billArray.length-1; i++){
			System.out.println(billArray[i].toString());
		}
		
		//output 2
		MyBillsClient client = new MyBillsClient();
		System.out.println("January's total expenses: "+ client.totalExpensesPerMonth(billArray)[0]);
		System.out.println("February's total expenses: "+ client.totalExpensesPerMonth(billArray)[1]);
		System.out.println("March's total expenses: "+ client.totalExpensesPerMonth(billArray)[2]);
		System.out.println("April's total expenses: "+ client.totalExpensesPerMonth(billArray)[3]);
	}
	
	public String[] totalExpensesPerMonth(MyBills[] myBillsArray){
		double jan = 0.0;
		double feb = 0.0;
		double mar = 0.0;
		double apr = 0.0;
		for(int i = 0; i<= myBillsArray.length-1; i++){
			if(myBillsArray[i].getMonth().equals("JANUARY")){
				for(int j = 0; j<= myBillsArray[i].getExpenses().length-1; j++)
					jan = jan + myBillsArray[i].getExpenses()[j];
			}
			if(myBillsArray[i].getMonth().equals("FEBRUARY")){
				for(int j = 0; j<= myBillsArray[i].getExpenses().length-1; j++)
					feb = feb + myBillsArray[i].getExpenses()[j];
			}
			if(myBillsArray[i].getMonth().equals("MARCH")){
				for(int j = 0; j<= myBillsArray[i].getExpenses().length-1; j++)
					mar = mar + myBillsArray[i].getExpenses()[j];
			}
			if(myBillsArray[i].getMonth().equals("APRIL")){
				for(int j = 0; j<= myBillsArray[i].getExpenses().length-1; j++)
					apr = apr + myBillsArray[i].getExpenses()[j];
			}
		}
		NumberFormat f = NumberFormat.getCurrencyInstance();
		String[] output = {f.format(jan), f.format(feb), f.format(mar), f.format(apr)};
		return output;
	}
}