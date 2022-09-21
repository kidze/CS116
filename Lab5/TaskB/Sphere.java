public class Sphere extends Circle{
	public Sphere(){
		super();
	}
	public Sphere(int ra){
		super(ra);
	}
	public Sphere(Circle c){
		super(c.getRadius());
	}
	public double volume(){
		return area()*(4/3)*r;
	}
	public String toString(){
		return super.toString() +"\nVolume: "+volume();
	}
}