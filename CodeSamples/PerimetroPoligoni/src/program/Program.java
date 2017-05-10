package program;

public class Program {
	public static void main(String[] s) {

		Polygon[] ps = new Polygon[3];

		// un primo semplice esempio di polimorfismo!
		ps[0] = new Triangle(3.0, 4.0, 5.0);
		ps[1] = new Rectangle(2.5, 4.5);
		ps[2] = new Square(6.0);

		for (int i = 0; i < 3; i++)
			System.out.println(ps[i].perimeter());
	}
}