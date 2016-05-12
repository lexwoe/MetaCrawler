package idio.model;

import java.util.regex.Pattern;

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
	
	/**
	 * some patterns for locating targets
	 */
	public static final Pattern HTML_H_S = Pattern.compile("<h\\d+\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_E = Pattern.compile("(.*)</h\\d+>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H34_S = Pattern.compile("<h[34]\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H34_E = Pattern.compile("(.*)</h[34]>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_S = Pattern.compile("<p\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_E = Pattern.compile("(.*)</p>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_S = Pattern.compile("<div\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_E = Pattern.compile("(.*)</div>", Pattern.CASE_INSENSITIVE);

	// patterns
	private Pattern2Box[] pats;
	
	// allow targeting or not
	private boolean flag = false;
	
	public void loadPattern(Pattern2Box[] pats){
		this.pats = pats;
	}
	
	/**
	 * detect target section
	 * @param line
	 * @return
	 */
	public boolean detect(String line){
		if(pats!=null){
			// detect starting marks of target
			if (this.pats[0].getP_s()!=null && this.pats[0].getP_s().matcher(line).find()) {
				this.flag = true;
				return true;
			// detect ending marks of target
			} else if (this.pats[0].getP_e()!=null && this.pats[0].getP_e().matcher(line).find())
				this.flag = false;
		}
		return false;
	}

	public Pattern2Box[] getPats() {
		return pats;
	}

	public void setPats(Pattern2Box[] pats) {
		this.pats = pats;
	}

	public boolean allow() {
		return flag;
	}

	public void setAllow(boolean flag) {
		this.flag = flag;
	}
	
	
}
