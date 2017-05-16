package main.game.res;

import java.util.*;

/**
 * 
 */
public class Dado {

	private int dadoBianco;
	private int dadoArancione;
	private int dadoNero;
	
    /**
     * Default constructor
     */
    public Dado() {
    	this.dadoBianco=0;
    	this.dadoArancione=0;
    	this.dadoNero=0;
    }

    /**
     * 
     */
    public void lancia(){
    	dadoBianco=new Random().nextInt(6-1)+1;
    	dadoArancione=new Random().nextInt(6-1)+1;
    	dadoNero=new Random().nextInt(6-1)+1;
    };
    
	public int getDadoBianco() {
		return dadoBianco;
	}

	public int getDadoArancione() {
		return dadoArancione;
	}

	public int getDadoNero() {
		return dadoNero;
	}

    

}