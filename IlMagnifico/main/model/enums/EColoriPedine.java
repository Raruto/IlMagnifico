package main.model.enums;

public enum EColoriPedine {
	BLACK(0), ORANGE(1), WHITE(2), NEUTRAL(3);
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
