package main.game.enums;

public enum CarteImpresa {
	ESEMPIO("nome",0,0,0,0,0,0,0,0,"effetto_immediato","effetto_permanente");
	private String nomeCarta;
	private int periodoCarta;
	private int legna;
	private int pietra;
	private int servitori;
	private int monete;
	private int PM;
	private int PV;
	private int PF;
	private String effettoImmediato;
	private String effettoPermanente;
	
	private CarteImpresa(String nome,int periodo,int legna,int pietra,int servitori,int monete,int PM,int PV,int PF,String effImm,String effPerm){
		this.nomeCarta=nome;
		this.periodoCarta=periodo;
		this.legna=legna;
		this.pietra=pietra;
		this.servitori=servitori;
		this.monete=monete;
		this.PM=PM;
		this.PV=PV;
		this.PF=PF;
		this.effettoImmediato=effImm;
		this.effettoPermanente=effPerm;
	}
	
	public String getNomeCarta(){
		return this.nomeCarta;
	}
	
	public int getPeriodoCarta(){
		return this.periodoCarta;
	}
	public int getLegna(){
		return this.legna;
	}
	public int getPietra(){
		return this.pietra;
	}
	public int getServitori(){
		return this.servitori;
	}
	public int getMonete(){
		return this.monete;
	}
	public int getPM(){
		return this.PM;
	}
	public int getPV(){
		return this.PV;
	}
	public int getPF(){
		return this.PF;
	}
	public String getEffImm(){
		return this.effettoImmediato;
	}
	public String getEffPerm(){
		return this.effettoPermanente;
	}
}
