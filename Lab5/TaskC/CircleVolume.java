public abstract class CircleVolume{
	protected int r;
	public CircleVolume(){
		r = 0;
	}
	public CircleVolume(int r){
		this.r = r;
	}
	public CircleVolume(CircleVolume cv){
		this.r = cv.getRadius();
	}
	public int getRadius(){return r;}
	public void setRadius(int r){this.r = r;}
	public double circumference(){
		double rr = (double) r;
		double output = 2*(Math.PI)*r;
		return output;
	}
	public double area(){
		double rr = (double) r;
		double output = (Math.PI)*r*r;
		return output;
	}
	public String toString(){
		return "Radius: "+r+"\nCircumference: "+circumference()+"\nArea: "+area();
	}
	public abstract double getVolume();
}