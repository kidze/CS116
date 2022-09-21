import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class EventSimulator implements ApplianceConstants{
	public static Vector<Appliance> appliances;
	public static int maximum;
	public static int[] locations;
	public static Vector<String> detailedReport;
	
	public static int getRandInt(char type, int x, int y) {														//Professor's method
		int num;
		switch (type) { 
		case 'U': case 'u':		// Uniform Distribution
			num = (int)(x + (Math.random()*(y+1-x))); 
			break;
		case 'E': case 'e':		// Exponential Distribution
			num = (int)(-1*x*Math.log(Math.random()));  
			break;	
		case 'N': case 'n':		// Normal Distribution
			num = (int)( x +
                (y * Math.cos(2 * Math.PI * Math.random()) *
                Math.sqrt(-2 * Math.log(Math.random()))));
			break;			
		default:				// Uniform Distribution
			num = (int)(x + (Math.random()*(y+1-x))); 
		}
		return num;
	}
	
	public static int totalWattageUsing(){															//calculate total wattage using at the moment
		int total = 0;
		for(int i = 0; i<= appliances.size()-1; i++){
			total = total + appliances.elementAt(i).power();
		}
		return total;
	}
	
	public static void setFirstState(double randomNumber){											//set initial state of appliances
		for(int i = 0; i<= appliances.size()-1; i++){
			appliances.elementAt(i).setFirstState(randomNumber);
		}
	}
	
	public static int calculateAllLow(){										//calculate total wattage if all smart appliances are turned LOW
		int total = 0;
		for(int i = 0; i<= appliances.size()-1; i++){
			if(appliances.elementAt(i).getType().equals(REGULAR) && appliances.elementAt(i).getState().equals(ON)){
				total = total + appliances.elementAt(i).power();
			}
		}
		for(int i = 0; i<= appliances.size()-1; i++){
			if(appliances.elementAt(i).getType().equals(SMART) && appliances.elementAt(i).getState().equals(ON)){
				total = total + appliances.elementAt(i).getLowWattage();
			}
		}
		return total;
	}
	
	public static void sortProbability(){												//sort in increasing order of probability
		for(int i = 0; i<= appliances.size()-2; i++){
			for(int j = i +1; j<= appliances.size()-1; j++){
				if(appliances.elementAt(i).getProbability() > appliances.elementAt(j).getProbability()){
					Appliance app = appliances.elementAt(i);
					appliances.setElementAt(appliances.elementAt(j),i);
					appliances.setElementAt(app, j);
				}
			}
		}
	}
	
	public static void sortLocations(){												//sort locations in order of increasing average probability
		double[] averageProb = new double[locations.length];
		for(int i = 0; i <= locations.length-1; i++){
			int counter = 0;
			double totalprob = 0;
			for(int j = 0; j<= appliances.size()-1; j++){
				if(appliances.elementAt(j).getLocation() == i){
					totalprob = totalprob + appliances.elementAt(j).getProbability();
					counter++;
				}
			}
			double average = totalprob/counter;
			averageProb[i] = average;
		}
		for(int i = 0; i <= averageProb.length-2; i++){
			for(int j = i+1; j<=averageProb.length-1; j++){
				if(averageProb[i] > averageProb[j]){
					double ave = averageProb[i];
					int loc = locations[i];
					averageProb[i] = averageProb[j];
					locations[i] = locations[j];
					averageProb[j] = ave;
					locations[j] = loc;
				}
			}
		}
		Vector<Appliance> vector = new Vector<Appliance>();
		for(int i = 0; i <= locations.length-1; i++){
			for(int j = 0; j <= appliances.size()-1; j++){
				if(appliances.elementAt(j).getLocation() == locations[i]){
					vector.add(appliances.elementAt(j));
				}
			}
		}
		for(int i = 0; i<= appliances.size()-1; i++){
			appliances.setElementAt(vector.elementAt(i),i);
		}
	}
	
	public static void control(int time){												//perform smart fix and save log
		detailedReport.add("At minute "+time+":\n\n");
		if(totalWattageUsing() > maximum){
			if(calculateAllLow() <= maximum){									//turning ON smart appliances to LOW
				sortProbability();
				while(totalWattageUsing() > maximum){
					int firstONindex = -1;
					for(int i = appliances.size()-1 ; i >= 0; i--){
						if(appliances.elementAt(i).getState().equals(ON) && appliances.elementAt(i).getType().equals(SMART)){
							firstONindex = i;
						}
					}
					appliances.elementAt(firstONindex).setState(LOW);
					detailedReport.add("Smart appliance with ID: "+appliances.elementAt(firstONindex).getUniqueID()+" was turned to LOW state.\n");
				}
			}
			else{																	//turn all ON & SMARTto LOW and start browning out
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getType().equals(SMART) && appliances.elementAt(i).getState().equals(ON)){
						appliances.elementAt(i).setState(LOW);
					}
				}
				detailedReport.add("All ON Smart appliances were turned to LOW.\n");
				sortLocations();
				while(totalWattageUsing() > maximum){
					int firstONorLOWLocation = -1;
					for(int i = locations.length-1; i >= 0; i--){
						for(int j= appliances.size()-1; j>=0; j--){
							if(appliances.elementAt(j).getLocation() == locations[i] && (appliances.elementAt(j).getState().equals(ON) || appliances.elementAt(j).getState().equals(LOW))){
								firstONorLOWLocation = locations[i];
							}
						}
					}
					for(int i = 0; i <= appliances.size()-1; i++){							//browning out
						if(appliances.elementAt(i).getLocation() == firstONorLOWLocation){
							appliances.elementAt(i).setState(OFF);
						}
					}
					detailedReport.add("Location "+firstONorLOWLocation+" was browned out.\n");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		appliances = new Vector<Appliance>();
		maximum = 0;
		detailedReport = new Vector<String>();
		final int ARRIVAL_MEAN=5;
		int simulationLength=0, minute=0, nextArrivalTime, callCount=0; 
		Scanner input = new Scanner(System.in);
	
		while (simulationLength<=0)	{																			//ask for simulationLength
			System.out.print("How many minutes long is the simulation? "); 

			while (!input.hasNextInt()) {
				input.next();
				System.out.print("Please enter an integer: ");
			}
			simulationLength = input.nextInt();
		}
		
		while(maximum<=0){																							//ask for maximum consumption
			System.out.println("Please set your wattage limit:");
			while(!input.hasNextInt()){
				input.next();
				System.out.print("Please enter an integer: ");
			}
			maximum = input.nextInt();
		}
		
		try{																											//read output.txt
			File file = new File("output.txt");
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				StringTokenizer line = new StringTokenizer(scanner.nextLine(),",");
				int location  = Integer.parseInt(line.nextToken());
				String name = line.nextToken();
				int onwatt = Integer.parseInt(line.nextToken());
				double prob = Double.parseDouble(line.nextToken());
				boolean smart = Boolean.parseBoolean(line.nextToken());
				double reductionpercentage = Double.parseDouble(line.nextToken());
				
				if(smart){
					appliances.add(new SmartAppliance(location,name,onwatt,prob,reductionpercentage));
				}else if(!smart){
					appliances.add(new RegularAppliance(location,name,onwatt,prob));
				}
			}
		}catch(IOException e){
			System.out.println("Cannot read file");
		}
		
		locations = new int[appliances.lastElement().getLocation() - appliances.firstElement().getLocation() + 1];		//put locations in array
		for(int i = 0; i <= locations.length-1; i++){
			locations[i] = appliances.firstElement().getLocation() + i;
		}
		boolean userMenu = true;
		while(userMenu){																								//user menu
			Scanner menuInput = new Scanner(System.in);
			System.out.println("What would you like to do:");
			System.out.println("Type \"A\" to use an input files to add appliances");
			System.out.println("Type \"a\" to add an appliance");
			System.out.println("Type \"D\" to delete an appliance");
			System.out.println("Type \"F\" to find and display an appliance");
			System.out.println("Type \"V\" to view all appliances of a location:");
			System.out.println("Type \"R\" to view all REGULAR appliances:");
			System.out.println("Type \"S\" to view all SMART aplliances:");
			System.out.println("Type \"E\" to exit the user menu and start the EVENT SIMULATOR");
			String option = menuInput.nextLine();
			
			switch(option){
				case "A":																								//add a file
				System.out.println("Please type the file name:");
				String filename = menuInput.nextLine();
				try{
					File file = new File(filename);
					Scanner scanner = new Scanner(file);
					while(scanner.hasNextLine()){
						StringTokenizer line = new StringTokenizer(scanner.nextLine(),",");
						int location  = Integer.parseInt(line.nextToken());
						String name = line.nextToken();
						int onwatt = Integer.parseInt(line.nextToken());
						double prob = Double.parseDouble(line.nextToken());
						boolean smart = Boolean.parseBoolean(line.nextToken());
						double reductionpercentage = Double.parseDouble(line.nextToken());
						//add appliance
						if(smart){
							appliances.add(new SmartAppliance(location,name,onwatt,prob,reductionpercentage));
						}else if(!smart){
							appliances.add(new RegularAppliance(location,name,onwatt,prob));
						}
					}
				}catch(IOException e){
					System.out.println("Cannot read file");
				}
				break;
				//////////////
				case "a":																								//add an appliance
				System.out.println("Enter the location: (integer)");
				int location = Integer.parseInt(menuInput.nextLine());
				System.out.println("Enter appliance name:");
				String name = menuInput.nextLine();
				System.out.println("Enter ON wattage:");
				int ow = Integer.parseInt(menuInput.nextLine());
				System.out.println("Enter ON/OFF probability of the appliance:");
				double prob = Double.parseDouble(menuInput.nextLine());
				System.out.println("This is a SMART appliance? (Enter either TRUE or FALSE)");
				boolean smart = Boolean.parseBoolean(menuInput.nextLine());
				if(smart){
					System.out.println("Nice it is a smart appliance! Please enter the reduction percentage:");
					double rp = Double.parseDouble(menuInput.nextLine());
					appliances.add(new SmartAppliance(location, name, ow,prob,rp));
				}else{
					appliances.add(new RegularAppliance(location,name,ow,prob));
				}
				System.out.println("Your appliance has been successfully added!\n");
				break;
				/////////////////////
				case "D":																								//delete a file
				System.out.println("Please enter the ID of the appliance you want to delete:");
				int id = Integer.parseInt(menuInput.nextLine());
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getUniqueID() == id)
						System.out.println(appliances.elementAt(i).toString());
				}
				System.out.println("Are you sure you want to delete this appliance? (\"Y\" for Yes and \"N\" for No)");
				String answer = menuInput.next();
				if(answer.equals("Y") || answer.equals("y")){
					for(int i = 0; i<= appliances.size()-1; i++){
						if(appliances.elementAt(i).getUniqueID() == id){
							appliances.removeElementAt(i);
							System.out.println("Appliance removed successfully!");
						}
					}
				}else{
					System.out.println("No? okay\n");
				}
				break;
				/////////////////////////
				case "F":																						//find an appliance
				System.out.println("Please enter the ID of the appliance you want to find:");
				int findID = Integer.parseInt(menuInput.nextLine());
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getUniqueID() == findID)
						System.out.println("Here is the appliance you are looking for:\n"+appliances.elementAt(i).toString());
				}
				break;
				////////////////////////
				case "V":																					//view appliances of a location
				int loc =  0;
				while(loc <=0){
					System.out.println("Please enter the location you want to view");
					while(!menuInput.hasNextInt()){
						System.out.println("Integer please:");
						menuInput.next();
					}
					loc = menuInput.nextInt();
				}
				int valid = 0;
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getLocation() == loc){
						valid++;
					}
				}
				if(valid > 0){
					System.out.println("Location found! Below is the data of appliances in that location:\n");
					for(int i = 0; i<= appliances.size()-1; i++){
						if(appliances.elementAt(i).getLocation() == loc){
						System.out.println(appliances.elementAt(i).toString());
						}
					}
				}else{
					System.out.println("Oops! Can't find the location you entered.\n");
				}
				break;
				////////////////////////
				case "R":																						//view all Regular
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getType().equals(REGULAR)){
						System.out.println(appliances.elementAt(i).toString());
					}
				}
				break;
				////////////////////////
				case "S":																						//view all Smart
				for(int i = 0; i<= appliances.size()-1; i++){
					if(appliances.elementAt(i).getType().equals(SMART)){
						System.out.println(appliances.elementAt(i).toString());
					}
				}
				break;
				////////////////////////
				case "E":																						//exit and start EVENT SIMULATOR
				System.out.println("Are you sure you want to quit the user menu and start the EVENT SIMULATOR? (\"Y\" for Yes and \"N\" for No)");
				String answer2 = menuInput.next();
				if(answer2.equals("Y") || answer2.equals("y")){
					System.out.println("Okay the EVENT SIMULATOR starts now!\n");
					userMenu = false;
				}else{
					System.out.println("No? okay\n");
				}
				break;
			}
		}

		nextArrivalTime = minute + getRandInt('E', ARRIVAL_MEAN, 0);
		
		while(minute<=simulationLength) {																		//EVENT SIMULATOR
			while ((minute == nextArrivalTime) && (minute<=simulationLength)) {
				callCount++;
				System.out.println("Minute:"+minute+" Event#"+callCount);
				nextArrivalTime=minute+getRandInt('E', ARRIVAL_MEAN, 0);
				
				setFirstState(Math.random());
				control(minute);
				System.out.println(totalWattageUsing());
			}
			minute++;
		} 
		System.out.println("Number of events = " + callCount);
		int numberOfReg = 0, numberOfSma = 0;
		for(int i = 0; i<= appliances.size()-1; i++){
			if(appliances.elementAt(i).getType().equals(REGULAR))
				numberOfReg++;
			else if(appliances.elementAt(i).getType().equals(SMART))
				numberOfSma++;
		}
		System.out.println("\nSummary report:\nTotal number of locations: "+locations.length+"\nNumber of Regular appliances: "+numberOfReg+"\nNumber of Smart aplliances: "+numberOfSma);
		
		try{
			FileWriter writer = new FileWriter("DetailedReport.txt",false);
			BufferedWriter bw = new BufferedWriter(writer);
			for(int i = 0; i <= detailedReport.size()-1; i++){
				bw.write(detailedReport.elementAt(i));
				bw.newLine();
			}
			bw.close();
		}catch(IOException e){
			System.out.println("Cannot write detailed report");
		}
	}
}