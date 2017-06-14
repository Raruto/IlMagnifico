package main.model.enums;

import main.util.ANSI;

public enum EColoriGiocatori {
	RED(ANSI.RED, ANSI.BACKGROUND_RED, "rosso"), GREEN(ANSI.GREEN,ANSI.BACKGROUND_GREEN,"verde"), BLUE(ANSI.BLUE,ANSI.BACKGROUND_BLUE,"blu"), YELLOW(ANSI.YELLOW,ANSI.BACKGROUND_YELLOW,"giallo");

	private String ANSICode;
	private String ANSIBackgroundCode;
	private String SwingName;

	private EColoriGiocatori(String ANSICode, String ANSIBackgroundCode, String SwingName) {
		this.ANSICode = ANSICode;
		this.ANSIBackgroundCode = ANSIBackgroundCode;
		this.SwingName = SwingName;
	}

	public String getANSI() {
		return this.ANSICode;
	}

	public String getANSIBackground() {
		return this.ANSIBackgroundCode;
	}

	public String getSwingName(){
		return this.SwingName;
	}
}
