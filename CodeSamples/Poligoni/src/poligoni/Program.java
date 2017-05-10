package poligoni;

public class Program {
	
	public static void main (String[] args)	{
		
		Point p1 = new Point (2.0,2.1);
		Point p2 = new Point (100.0,3.1);
				
		Polygon poly = new Polygon(2);
		poly.setVertice(p1, 0);
		poly.setVertice(p2, 1);
		
		System.out.println(p1.distanzaDa(p2));
		
		System.out.println(poly.getPerimeter());		
	}

}
