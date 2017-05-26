package LorenzoIlMagnifico.old.game.board;

import java.util.*;

import LorenzoIlMagnifico.old.game.board.council.PalazzoConsiglio;
import LorenzoIlMagnifico.old.game.board.harvest.AreaRaccolta;
import LorenzoIlMagnifico.old.game.board.market.Mercato;
import LorenzoIlMagnifico.old.game.board.production.AreaProduzione;
import LorenzoIlMagnifico.old.game.board.tower.AreaTorre;

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