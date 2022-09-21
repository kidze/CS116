import java.lang.Math;
public class Square extends GeometricFigure implements SidedObject{
	public Square(){
		width = (int)(Math.random()*10);
		height = (int)(Math.random()*10);
	}
	public void displaySides(){
		System.out.println(""+4);
	}
	public int calculateArea(){
		return width*height;
	}
}