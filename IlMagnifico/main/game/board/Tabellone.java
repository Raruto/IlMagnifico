package main.game.board;

import java.util.*;

import main.game.board.council.PalazzoConsiglio;
import main.game.board.harvest.AreaRaccolta;
import main.game.board.market.Mercato;
import main.game.board.production.AreaProduzione;
import main.game.board.tower.AreaTorre;

/**
 * 
 */
public class Tabellone {

	/**
	 * Default constructor
	 */
	public Tabellone() {
	}

	/**
	 * 
	 */
	private AreaTorre torri;

	/**
	 * 
	 */
	private AreaRaccolta areaRaccolta;

	/**
	 * 
	 */
	private AreaProduzione areaProduzione;

	/**
	 * 
	 */
	private Mercato mercato;

	/**
	 * 
	 */
	private PalazzoConsiglio palazzoDelConsiglio;

	/**
	 * 
	 */
	public AreaTorre getTorri() {
		return this.torri;
	}

	/**
	 * 
	 */
	public PalazzoConsiglio getPalazzoDelConsiglio() {
		return this.palazzoDelConsiglio;
	}

}