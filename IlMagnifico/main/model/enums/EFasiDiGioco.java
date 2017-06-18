package main.model.enums;

public enum EFasiDiGioco {
	InizioPartita("GameStarted"), 	 InizioPeriodo("PeriodStarted"), 
	InizioTurno("TurnStarted"), 	 MossaGiocatore("PlayerMove"), 
	FineTurno("TurnEnded"), 		 FinePeriodo("PeriodEnded"), 
	SostegnoChiesa("ChurchSupport"), FinePartita("GameEnded");

	private String name;

	private EFasiDiGioco(String name) {
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
}
