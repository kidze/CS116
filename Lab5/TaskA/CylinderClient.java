public class CylinderClient{
	public static void main(String[] args){
		Circle circle1 = new Circle();
		System.out.println(circle1.toString()+"\n");
		
		Cylinder cylinder1 = new Cylinder();
		System.out.println(cylinder1.toString()+"\n");
		
		Cylinder cylinder2 = new Cylinder(circle1, 2);
		System.out.println(cylinder2.toString()+"\n");
		
		circle1.setRadius(5);
		System.out.println(circle1.toString()+"\n");
		
		System.out.println("Circumference: "+cylinder1.circumference()+". Area: "+cylinder1.area());
	}
}