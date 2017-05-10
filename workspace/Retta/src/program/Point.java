package program;

public class Point {
	public final double X, Y;

	public Point() {
		this(0, 0);
	}

	public Point(double x, double y) {
		this.X = x;
		this.Y = y;
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		return distance(new Point(x1, y1), new Point(x2, y2));
	}

	public static double distance(Point p1, Point p2) {
		double difx, dify, distanza;

		difx = p2.X - p1.X; /// Sottraggo le x
		difx = Math.pow(difx, 2); /// elevo al quadrato
		dify = p2.Y - p1.Y; /// Sottraggo le y
		dify = Math.pow(dify, 2); /// elevo al quadrato

		distanza = difx + dify; /// Sommo i risultati
		distanza = Math.sqrt(distanza); /// Distanza = radice quadrata del
										/// risultato

		return distanza;
	}

	public static double distanceX(double x1, double x2) {
		return distanceX(new Point(x1, 0), new Point(x2, 0));
	}

	public static double distanceX(Point p1, Point p2) {
		return distanceOverAxis(p2.X, p1.X);
	}

	public static double distanceY(double y1, double y2) {
		return distanceY(new Point(0, y1), new Point(0, y2));
	}

	public static double distanceY(Point p1, Point p2) {
		return distanceOverAxis(p2.Y, p1.Y);
	}

	private static double distanceOverAxis(double l1, double l2) {
		return l2 - l1;
	}

}
