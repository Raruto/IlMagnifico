package main.model.enums;

import java.util.ArrayList;

public enum EScomuniche {
	
	//Prima fila
	RICEVI_MENO_PM(				   "Receive less MP", 			 "scomunica 1_1", 1), 
	MENO_QUATTRO_ON_TERRITORIO(	   "-4 on Territory", 			 "scomunica 1_2", 2), 
	NO_PV_PERSONAGGIO(			   "No Charachter VP", 			 "scomunica 1_3", 3);/*,
	
	//;
	//Seconda fila
	RICEVI_MENO_MONETE(			   "Receive less C", 	 		 "scomunica 2_1", 1),
	MENO_QUATTRO_ON_EDIFICIO(	   "-4 on Building", 	 		 "scomunica 2_2", 2),
	NO_PV_IMPRESA(				   "No Venture VP", 	 	 	 "scomunica 2_3", 3),
	
	//;
	//Terza fila
	RICEVI_MENO_SERVITORI(		   "Receive less Sv", 	 		 "scomunica 3_1", 1),
	MENO_QUATTRO_ON_PERSONAGGIO(   "-4 on Charachter",	 		 "scomunica 3_2", 2),
	NO_PV_TERRITORIO(			   "No Territory VP", 	 		 "scomunica 3_3", 3),
	
	//;
	//Quarta fila
	RICEVI_MENO_LEGNA_PIETRA(	   "Receive less W, St",	 	 "scomunica 4_1", 1),
	MENO_QUATTRO_ON_IMPRESA(	   "-4 on Venture",	 	 		 "scomunica 4_2", 2),
	PV_MENO_CINQUE_PV(			   "VP-5 foreach VP",	 		 "scomunica 4_3", 3),
	
	//;
	//Quinta fila
	MENO_TRE_ON_RACCOLTO(		   "-3 on Harvest",	 	 		 "scomunica 5_1", 1),
	NO_MERCATO(					   "No Market",	 	 	 		 "scomunica 5_2", 2),
	PV_MENO_UNO_PM(				   "VP-1 foreach MP",	 		 "scomunica 5_3", 3),

	//;
	//Sesta fila
	MENO_TRE_ON_PRODUZIONE(		   "-3 on Production",	 		 "scomunica 6_1", 1),
	DIVISO_DUE_ON_INCREMENTO( 	   "Sv/2 on Increment", 	 	 "scomunica 6_2", 2),
	PV_MENO_UNO_LEGNO_PIETRA_CARTE("VP-1 foreach W, St cards",	 "scomunica 6_3", 3),
	
	//;
	//Settima fila
	MENO_UNO_ON_FAMIGLARI(		   "-1 on Familiars",	 		 "scomunica 7_1", 1),
	SALTA_PRIMA_AZIONE_TURNO( 	   "Skip the first move", 		 "scomunica 7_2", 2),
	PV_MENO_UNO_RISERVA(	  	   "VP-1 foreach W, St, Sv, C",	 "scomunica 7_3", 3);
    */

	private String nome;
	private int periodo;
	private String nomeFile;
	private ArrayList<Object[]> effetto;

	private EScomuniche(String nome, String nomeFile, int periodo) {
		this.nome = nome;
		this.periodo = periodo;
		this.nomeFile = nomeFile;
		this.effetto = new ArrayList<Object[]>();
		inizializzaEffetto();
	}

	public void inizializzaEffetto() {
		for (EEffettiScomuniche e : EEffettiScomuniche.values()) {
			if (this.nome.equals(e.getNome())) {
				this.effetto.add(e.getEffetto());
			}
		}

	}

	public int getPeriodo() {
		return this.periodo;
	}

	public String getNome() {
		return this.nome;
	}

	public ArrayList<Object[]> getEffetto() {
		return this.effetto;
	}

	public String getNomeFile() {
		return this.nomeFile;
	}
}
