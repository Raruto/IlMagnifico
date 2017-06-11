package res.old.main.model.game.board;

import java.util.*;

import res.old.main.model.game.board.council.PalazzoConsiglio;
import res.old.main.model.game.board.harvest.AreaRaccolta;
import res.old.main.model.game.board.market.Mercato;
import res.old.main.model.game.board.production.AreaProduzione;
import res.old.main.model.game.board.tower.AreaTorre;

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