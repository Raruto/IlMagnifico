package poligoni;

public class Polygon {
	public Point[] vertici;
	
	public Polygon(int nVertici){
		vertici = new Point[nVertici];		
	}
	
	public void setVertice(Point p, int pointIndex){
		if(pointIndex<=vertici.length-1){
		vertici[pointIndex] = p;
		}
	}
	public double getPerimeter(){
		double perimeter=0.0;
		for (int i = 0; i < vertici.length-1; i++) {
			perimeter += vertici[i].distanzaDa(vertici[i+1]);
		}
		perimeter += vertici[vertici.length-1].distanzaDa(vertici[0]);
		return perimeter;		
	}

}
