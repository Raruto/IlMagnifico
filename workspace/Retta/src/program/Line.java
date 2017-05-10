package program;

public class Line extends Point {

	public final double M, Q;

	public Line() {
		this(new Point(0, 0), new Point(1, 1));
	}

	public Line(double x1, double y1, double x2, double y2) {
		this(new Point(x1, y1), new Point(x2, y2));
	}

	public Line(Point p1, Point p2) {
		this(gradient(p1, p2), yIntercept(p1, p2));
	}

	public Line(double m, double q) {
		this.M = m;
		this.Q = q;
	}

	public static double gradient(double x1, double y1, double x2, double y2) {
		return Line.gradient(new Point(x1, y1), new Point(x2, y2));
	}

	public static double gradient(Point p1, Point p2) {
		return Point.distanceY(p1, p2) / Point.distanceY(p1, p2);
	}

	public static double yIntercept(double x1, double y1, double x2, double y2) {
		return Line.yIntercept(new Point(x1, y1), new Point(x2, y2));
	}

	public static double yIntercept(Point p1, Point p2) {
		return (-gradient(p1, p2) * p1.X) + p1.Y;

	}

	public String toString() {
		return "y = " + this.M + "x + " + this.Q;
	}

	public static String getLineInfo(double m, double q) {
		String info = "";

		if (m == 0) {
			info += "Retta orizzontale" + "\n";
		}

		if (Double.isInfinite(m)) {
			info += "Retta verticale" + "\n";
		}

		if (Double.isNaN(m)) {
			info += "Fascio infinito di rette" + "\n";
		}
		if (q == 0) {
			if (m > 0) {
				info += "La retta insiste tra il 1° e il 3° quadrante" + "\n";
			}

			if (m < 0) {
				info += "La retta insiste tra il 2° e il 4° quadrante" + "\n";
			}

			if (m == 1) {
				info += "La retta è bisettrice del 1° e 3° quadrante" + "\n";
			}

			if (m == -1) {
				info += "La retta è bisettrice del 2° e 4° quadrante" + "\n";
			}
		}

		if (q > 0) {
			if (m > 0) {
				info += "La retta insiste tra il 1°, 2° e 3° quadrante" + "\n";
			}
			if (m < 0) {
				info += "La retta insiste tra il 1°, 2° e 4° quadrante" + "\n";
			}
		}

		if (q < 0) {
			if (m > 0) {
				info += "La retta insiste tra il 1°, 3° e 4° quadrante" + "\n";
			}
			if (m < 0) {
				info += "La retta insiste tra il 2°, 3° e 4° quadrante" + "\n";
			}
		}
		return info;
	}

	public String getLineInfo() {
		return getLineInfo(this.M, this.Q);
	}

	public static boolean isPointOnLine(double xp, double yp, double m, double q) {
		return (yp - (m * xp) - q == 0);
	}

	public boolean isPointOnLine(double xp, double yp) {
		return isPointOnLine(xp, yp, this.M, this.Q);
	}

	public String getPointPositionOnLine(double xp, double yp) {
		String info = "";
		if (isPointOnLine(xp, yp)) {
			info = "Il punto insiste sulla retta";
		} else if (yp - (this.M * xp) - this.Q > 0) {
			info = "Il punto è sopra retta";
		} else if (yp - (this.M * xp) - this.Q < 0) {
			info = "Il punto è sotto retta";
		}
		return info;
	}
}
