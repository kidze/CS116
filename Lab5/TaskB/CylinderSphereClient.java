public class CylinderSphereClient{
	public static void main(String[] args){
		Cylinder cyl = new Cylinder(28,35);
		System.out.println(cyl.toString()+"\n");
		
		Sphere sph = new Sphere(6);
		System.out.println("Number of spheres in cylinder: "+(cyl.volume()/sph.volume())+"\n");
		
		Circle cir = new Circle(10);
		System.out.println(cir.toString()+"\n");
		
		Circle c1 = cyl;
		System.out.println(c1.toString()+"\n");
		Circle c2 = sph;
		System.out.println(c2.toString()+"\n");
		
		Circle[] cs = {new Circle(2), new Cylinder(2,5), new Sphere(8)};
		for(Circle c : cs){
			System.out.println(c.toString());
		}
	}
}