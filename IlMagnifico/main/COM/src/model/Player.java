package model;

public class Player {
	/**
	 * Nickname of the player.
	 */
	private String mNickname;

	/**
	 * Set the nickname of the player. This is used as uniqueId in game to
	 * identify the player.
	 * 
	 * @param nickname
	 *            to set to the player.
	 */
	public void setNickname(String nickname) {
		mNickname = nickname;
	}

	/**
	 * Get the nickname of the player.
	 * 
	 * @return the nickname of the player.
	 */
	public String getNickname() {
		return mNickname;
	}

}
