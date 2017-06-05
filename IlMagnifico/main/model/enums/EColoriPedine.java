package main.model.enums;

public enum EColoriPedine {
	Nera(0), Arancione(1), Bianca(2), Neutrale(3);
	private int indiceColore;

	private EColoriPedine(int indice) {
		this.indiceColore = indice;
	}

	public int getIndiceColore() {
		return this.indiceColore;
	}
	
	public static String stringify(){
		EColoriPedine[] c = EColoriPedine.values();
		String s="";

		for (int i = 0; i < c.length; i++) {
			s+="[" + c[i].toString() + "] ";
			if (i % 7 == 0 && i != 0)
				s+="\n";
		}
		return s;		
	}
}
