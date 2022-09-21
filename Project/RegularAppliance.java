public class RegularAppliance extends Appliance{
	public RegularAppliance(int l, String n, int ow, double prob){
		super(l,n,ow,prob);
		lowWattage = 0;
		type = REGULAR;
	}
	
	public int power(){
		if(state.equals(OFF))
			return 0;
		else
			return onWattage;
	}
	public String toString(){
		return super.toString()+"\nType: "+type+".\nState: "+state+"\n==========";
	}
}