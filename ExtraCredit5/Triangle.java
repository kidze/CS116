import java.lang.Math;
public class Triangle extends GeometricFigure implements SidedObject{
	public Triangle(){
		width = (int)(Math.random()*10);
		height = (int)(Math.random()*10);
	}
	public void displaySides(){
		System.out.println(""+3);
	}
	public int calculateArea(){
		return (int) (0.5*width*height);
	}
}