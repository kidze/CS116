public class Sphere extends CircleVolume{
	public Sphere(){
		super();
	}
	public Sphere(int ra){
		super(ra);
	}
	public Sphere(CircleVolume c){
		super(c.getRadius());
	}
	public double getVolume(){
		return area()*(4/3)*r;
	}
	public String toString(){
		return super.toString() +"\nVolume: "+getVolume();
	}
}