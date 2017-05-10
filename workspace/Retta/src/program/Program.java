package program;

import java.util.Scanner;

/// dati in input le coordinate di due punti stabilire:
/// - la distanza tra i due punti;
/// - l'equazione della retta, passante per i due punti;
/// - dato un altro punto, stabilire dove si trova rispetta alla retta;
/// - classificare la retta.
public class Program {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		double x1, y1, x2, y2, xp, yp; /// input
		double m, q, distanza; /// output
		double difx, dify; /// Differenza delle x e delle y

		System.out.print("Inserire x1: ");
		x1 = Double.parseDouble(scanner.nextLine());
		System.out.print("Inserire y1: ");
		y1 = Double.parseDouble(scanner.nextLine());

		System.out.println();

		System.out.print("Inserire x2: ");
		x2 = Double.parseDouble(scanner.nextLine());
		System.out.print("Inserire y2: ");
		y2 = Double.parseDouble(scanner.nextLine());

		System.out.println();

		/// Distanza tra due punti
		distanza = Point.distance(x1, y1, x2, y2);
		System.out.println("Distanza: " + distanza + "\n");
		
		/// Equazione della retta
		m = Line.gradient(x1, y1, x2, y2);
		q = Line.yIntercept(x1, y1, x2, y2);
		Line line = new Line(x1, y1, x2, y2);
		System.out.println("Equazione della retta: "+ line.toString());
		
		/// Classificazione della retta
		System.out.println(line.getLineInfo());
			

		/// Dato P stabilire dove si trova rispetto alla retta
		System.out.print("Inserire xp: ");
		xp = Double.parseDouble(scanner.nextLine());
		System.out.print("Inserire yp: ");
		yp = Double.parseDouble(scanner.nextLine());

		System.out.println(line.getPointPositionOnLine(xp, yp));
	}
}
