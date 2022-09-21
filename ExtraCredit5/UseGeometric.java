public class UseGeometric{
	public static void main(String[] args){
		GeometricFigure[] figures = new GeometricFigure[10];
		for(int i = 0; i<=figures.length-1;i++){
			if(i<=4)
				figures[i] = new Square();
			else
				figures[i] = new Triangle();
		}
		
		for(GeometricFigure f : figures){
			System.out.println(""+f.calculateArea());
		}
	}
}