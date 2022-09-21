public class CylinderSphereClient{
	public static void main(String[] args){
		Cylinder cyl = new Cylinder(4,6);
		Sphere sph = new Sphere(5);
		System.out.println(cyl.toString());
		System.out.println();
		System.out.println(sph.toString());
		System.out.println();
		
		CircleVolume c1 = cyl;
		CircleVolume c2 = sph;
		System.out.println(c1.area());
		System.out.println();
		System.out.println(c1.getVolume());
		System.out.println();
		
		System.out.println(c2.toString());
		System.out.println();
		System.out.println(c2.getVolume());
		System.out.println();
		
		CircleVolume[] cvs = new CircleVolume[4];
		cvs[0] = new Cylinder();
		cvs[1] = new Cylinder(8,2);
		cvs[2] = new Sphere(7);
		cvs[3] = new Sphere(9);
		for(CircleVolume cv : cvs){
			System.out.println(cv.toString()+"\n");
		}
	}
}