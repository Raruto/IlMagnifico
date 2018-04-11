package main.model.game.cards.development;

/**
 * DB delle Carte Edificio usabili nel gioco
 */
public enum CarteEdificio {
	ESEMPIO("nome", 0, 0, 0, 0, 0, 0, 0, 0, 0, "nome_effetto_immediato", "nome_effetto_permanente");
	private String nomeCarta;
	private int periodoCarta;
	private int legna;
	private int monete;
	private int pietra;
	private int servitori;
	private int puntiFede;
	private int puntiMilitari;
	private int puntiVittoria;
	private int sogliaPuntiMilitari;
	private String nomeEffettoImmediato;
	private String nomeEffettoPermanente;

	private CarteEdificio(String nome, int periodo, int legna, int monete, int pietra, int servitori, int puntiFede,
			int puntiMilitari, int puntiVittoria,int sogliaPuntiMilitari, String nomeEffettoImmediato, String nomeEffettoPermanente) {
		this.nomeCarta = nome;
		this.periodoCarta = periodo;
		this.legna = legna;
		this.pietra = pietra;
		this.servitori = servitori;
		this.monete = monete;
		this.sogliaPuntiMilitari=sogliaPuntiMilitari;
		this.puntiMilitari = puntiMilitari;
		this.puntiVittoria = puntiVittoria;
		this.puntiFede = puntiFede;
		this.nomeEffettoImmediato = nomeEffettoImmediato;
		this.nomeEffettoPermanente = nomeEffettoPermanente;
	}

	public String getNomeCarta() {
		return this.nomeCarta;
	}

	public int getPeriodoCarta() {
		return this.periodoCarta;
	}

	public int getLegna() {
		return this.legna;
	}

	public int getPietra() {
		return this.pietra;
	}

	public int getServitori() {
		return this.servitori;
	}

	public int getMonete() {
		return this.monete;
	}

	public int getSogliaPuntiMilitari() {
		return this.sogliaPuntiMilitari;
	}

	public int getPuntiFede() {
		return puntiFede;
	}

	public void setPuntiFede(int puntiFede) {
		this.puntiFede = puntiFede;
	}

	public int getPuntiMilitari() {
		return puntiMilitari;
	}

	public void setPuntiMilitari(int puntiMilitari) {
		this.puntiMilitari = puntiMilitari;
	}

	public int getPuntiVittoria() {
		return puntiVittoria;
	}

	public void setPuntiVittoria(int pintiVittoria) {
		this.puntiVittoria = pintiVittoria;
	}

	public String getNomeEffettoImmediato() {
		return this.nomeEffettoImmediato;
	}

	public String getNomeEffettoPermanente() {
		return this.nomeEffettoPermanente;
	}
}
