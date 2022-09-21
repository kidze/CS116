public class SmartAppliance extends Appliance{
	
	double reductionPercent;
	public SmartAppliance(int l, String n, int ow, double prob, double r){
		super(l,n,ow,prob);
		reductionPercent = r;
		lowWattage = calculateLowWattage();
		type = SMART;
	}
	
	public int calculateLowWattage(){
		double a = onWattage*reductionPercent;
		return (int) a;
	}
	
	public int power(){
		if(state.equals(ON))
			return onWattage;
		else if(state.equals(LOW))
			return lowWattage;
		else
			return 0;
	}
	
	public String toString(){
		return super.toString()+"\nType: "+type+". LOW wattage: "+lowWattage+".\nState: "+state+"\n==========";
	}
}