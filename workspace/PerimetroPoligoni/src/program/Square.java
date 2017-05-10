package program;

public class Square extends Polygon {
	private double lato;

	public Square(double lato) {
		this.lato = lato;
	}

	public double perimeter() {
		return (4 * lato);
	}
}