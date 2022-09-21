public abstract class Appliance implements ApplianceConstants{
	String name;
	String type;
	int onWattage;
	int location;
	double probability;
	static int id;
	int uniqueID;
	String state;
	int lowWattage;
	
	public Appliance(int l, String n, int ow, double prob){
		location = l;
		name = n;
		onWattage = ow;
		probability = prob;
		id++;
		uniqueID = id;
	}
	public int getUniqueID(){return uniqueID;}
	public int getOnWattage(){return onWattage;}
	public String getType(){return type;}
	public String getState(){return state;}
	public int getLowWattage(){return lowWattage;}
	public double getProbability(){return probability;}
	public int getLocation(){return location;}
	
	public void setState(String a){state = a;}
	
	public abstract int power();
	
	public void setFirstState(double possibility){
		if(probability < possibility)
			state = OFF;
		else
			state =  ON;
	}
	public String toString(){
		return "ID: "+uniqueID+". Name of appliance: "+name+". Location: "+location+". ON wattage: "+onWattage+". ON probability: "+probability;
	}
}