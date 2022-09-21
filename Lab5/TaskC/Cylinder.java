public class Cylinder extends CircleVolume{
	private float h;
	public Cylinder(){
		super();
		h = 0;
	}
	public Cylinder(int ra, float he){
		super(ra);
		h = he;
	}
	public Cylinder(CircleVolume c, float he){
		super(c.getRadius());
		h = he;
	}
	public float getHeight(){return h;}
	public void setHeight(float h){this.h = h;}
	public double getVolume(){
		return circumference()*h;
	}
	public String toString(){
		return super.toString() + "\nHeight: "+h+"\nVolume: "+getVolume();
	}
}