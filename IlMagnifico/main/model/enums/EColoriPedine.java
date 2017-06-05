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
}
