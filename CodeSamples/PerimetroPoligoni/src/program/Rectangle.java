package program;

public class Rectangle extends Polygon {
	private double l1, l2;

	public Rectangle(double l1, double l2) {
		this.l1 = l1;
		this.l2 = l2;
	}

	public double perimeter() {
		return (2 * (l1 + l2));
	}
}