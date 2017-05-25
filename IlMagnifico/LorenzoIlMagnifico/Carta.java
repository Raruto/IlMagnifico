package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public abstract class Carta {

    /**
     * Default constructor
     */
    public Carta(String nome,ArrayList<Object> aquisizione,ArrayList<Object> effettoImmediato,ArrayList<Object> effettoPermanente) {
    	this.nome
    }

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private ArrayList<Object> acquisizione;

    /**
     * 
     */
    private ArrayList<Object> effettoImmediato;

    /**
     * 
     */
    private ArrayList<Object> effettoPermanente;

    /**
     * @param oggetto 
     * @return
     */
    public boolean acquisibile(ArrayList<Object> oggetto) {
        // TODO implement here
        return false;
    }

    /**
     * @param Object 
     * @return
     */
    public void effettoImmediato(void Object) {
        // TODO implement here
        return null;
    }

    /**
     * @param Object 
     * @return
     */
    public void effettoPermanente(void Object) {
        // TODO implement here
        return null;
    }

}