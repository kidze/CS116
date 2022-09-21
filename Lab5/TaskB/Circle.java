public class Circle{
	protected int r;
	public Circle(){
		r = 0;
	}
	public Circle(int r){
		this.r = r;
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
}