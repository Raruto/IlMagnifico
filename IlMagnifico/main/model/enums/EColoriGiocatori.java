package main.model.enums;

import main.util.ANSI;

public enum EColoriGiocatori {
	RED(ANSI.RED, ANSI.BACKGROUND_RED), GREEN(ANSI.GREEN,ANSI.BACKGROUND_GREEN), BLUE(ANSI.BLUE,ANSI.BACKGROUND_BLUE), YELLOW(ANSI.YELLOW,ANSI.BACKGROUND_YELLOW);

	private String ANSICode;
	private String ANSIBackgroundCode;

	private EColoriGiocatori(String ANSICode, String ANSIBackgroundCode) {
		this.ANSICode = ANSICode;
		this.ANSIBackgroundCode = ANSIBackgroundCode;
	}

	public String getANSI() {
		return this.ANSICode;
	}

	public String getANSIBackground() {
		return this.ANSIBackgroundCode;
	}
}
