package main.game.cards.development;

import java.util.*;

import main.game.activatables.Effetti;
import main.game.activatables.Effetto;

/**
 * 
 */
public class CartaEdificio extends CartaSviluppo {
private CarteEdificio carta;

public CartaEdificio(CarteEdificio c){
	this.carta = c;
	this.nome = c.getNomeCarta();
	this.periodo = c.getPeriodoCarta();
	this.costo = new CostoCartaSviluppo(c.getLegna(), c.getPietra(), c.getServitori(), c.getMonete(), c.getPM(),
			c.getPV(), c.getPF());
	/*
	 * Guardo negli effetti quelli che hanno l'effetto principale indicato nella carta e riempio l'arraylist degli effetti
	 * indicati nelle alternative dell'effetto principale (es. con l'effetto PRIVILEGIO_DEL_CONSIGLIO scorro l'enum
	 * e riempio l'arraylist di tutti gli effetti alternativi di PRIVILEGIO_DEL_CONSIGLIO).
	 * Ditemi se può andare bene o se invece ho sbagliato qualcosa
	 * */
	for(Effetti eTemp1 : Effetti.values()){
		if(c.getEffImm()==eTemp1.getEffettoPrincipale()&&!(eTemp1.getEffettoAlternativo()==null)){
			for(Effetti eTemp2 : Effetti.values()){
				if(eTemp1.getEffettoAlternativo()==eTemp2.getEffettoPrincipale())
					this.effettoImmediato.add(0, new Effetto(eTemp2));
			}
		}
		if(c.getEffImm()==eTemp1.getEffettoPrincipale()&&eTemp1.getEffettoAlternativo()==null)
			this.effettoImmediato.add(0, new Effetto(eTemp1));
	}
	/*
	 * Faccio la stessa cosa con gli effetti permanenti
	 * */
	for(Effetti eTemp1 : Effetti.values()){
		if(c.getEffPerm()==eTemp1.getEffettoPrincipale()&&!(eTemp1.getEffettoAlternativo()==null)){
			for(Effetti eTemp2 : Effetti.values()){
				if(eTemp1.getEffettoAlternativo()==eTemp2.getEffettoPrincipale())
					this.effettoPermanente.add(0, new Effetto(eTemp2));
			}
		}
		if(c.getEffPerm()==eTemp1.getEffettoPrincipale()&&eTemp1.getEffettoAlternativo()==null)
			this.effettoPermanente.add(0, new Effetto(eTemp1));
	}
}
public CarteEdificio getCarta(){
	return this.carta;
}
}