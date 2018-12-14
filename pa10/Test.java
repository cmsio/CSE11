
public class Test {
	public static void main(String[] args) {
		ARectangle a1 = new Square(3, 10, 3);
		ARectangle a2 = new Square(3, 10, 3);
		System.out.println("is equals: " + a1.equals(a2));
		
		ARectangle a3 = new Rectangle(3, 3, 3, 3);
		ARectangle a4 = new Rectangle(3, 3, 3, 3);
		System.out.println("is equals: " + a3.equals(a4));
		
		Triangle t1 = new Triangle(new Point(1, 1), new Point(-3, -3), new Point(5, 5));
		Triangle t2 = new Triangle(new Point(1, 1), new Point(-3, -3), new Point(5, 5));
		Triangle t3 = new Triangle(t1);
		System.out.println("is equals: " + t1.equals(t2));
		System.out.println(t1.toString());
		System.out.println(t3.toString());
		System.out.println(a1.toString());
	}
}
