
public enum CartaPersonaggio {
     CAVALIERE("cavaliere",1,2,0,0);
	private String nomeCarta;
	private int periodoCarta;
	private int costoMonetePersonaggio;
	private int numeroEffettoImmediato;
	private int numeroEffettoPermanente;
	
	private CartaPersonaggio(String nome,int periodo,int costoMonete,int numeroEffettoImmediato,int numeroEffettoPermanente){
	this.nomeCarta=nome;
	this.periodoCarta=periodo;
	this.costoMonetePersonaggio=costoMonete;
	this.numeroEffettoImmediato=numeroEffettoImmediato;
	this.numeroEffettoPermanente=numeroEffettopermanente;
	}
	
	public void smistaEffettiImmediati(int numeroEffetto){
		if(numeroEffetto==0)
			privilegioDelConsiglio();
		
	}
	
	public void smistaEffettiPermanenti(int numeroEffettoPermanente){
		if(numeroEffetto==0)
		  aumentaImpresaDiDue();
	}
}
