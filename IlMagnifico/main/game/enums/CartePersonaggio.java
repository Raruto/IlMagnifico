package main.game.enums;

public enum CartePersonaggio {
ESEMPIO("nome",0);
	private String nomeCarta;
	private int periodoCarta;
	private CartePersonaggio(String nome,int periodo){
		this.nomeCarta=nome;
		this.periodoCarta=periodo;
	}
	public String getNomeCarta(){
		return this.nomeCarta;
	}
	
	public int getPeriodoCarta(){
		return this.periodoCarta;
	}
}
