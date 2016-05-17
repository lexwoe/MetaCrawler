package idio.model;

import idio.beans.Pattern2Box;

/**
 * 
 * @author lexwoe
 *
 * locate target section
 *
 * @version 16.05
 */

public class Model {
	
	public static final String M_MAY = "MAY";
	public static final String M_MUST = "MUST";

	// patterns
	private Pattern2Box[] pats;
	
	public void loadPattern(Pattern2Box[] pats){
		this.pats = pats;
	}
	
	/**
	 * detect target section
	 * @param line
	 * @return
	 */
	public boolean detect(String line){
		return false;
	}

	public Pattern2Box[] getPats() {
		return pats;
	}

	public void setPats(Pattern2Box[] pats) {
		this.pats = pats;
	}

	public boolean allow() {
		return true;
	}
	
}
