package Program;

import java.util.Random;

public enum Handsigns {
	ROCK, PAPER, SCISSORS;

	public static Handsigns getRandomSign() {
		return Handsigns.values()[new Random().nextInt(Handsigns.values().length)];
	}

	public String capitalizeFirstStringLetter() {
		return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
	}

	public static boolean isValidHandsign(String handsign) {
		return handsign.equals(Handsigns.ROCK.toString()) || 
				handsign.equals(Handsigns.PAPER.toString()) || 
				handsign.equals(Handsigns.SCISSORS.toString());
	}

}
