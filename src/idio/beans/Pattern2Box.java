package idio.beans;

import java.util.regex.Pattern;

/**
 * 
 * @author lexwoe
 * 
 * This is used to gather a pair of patterns for parsing or
 * detecting HTML contents. The parameter "id" is assigned 
 * for mapping the pattern result to corresponding variable
 * in "Box".
 * 
 * @version 16.05
 *
 */

public class Pattern2Box {

	private String id;
	private Pattern p_s;
	private Pattern p_e;
	
	public Pattern2Box(String id,Pattern p_s,Pattern p_e){
		this.id = id;
		this.p_s = p_s;
		this.p_e = p_e;
	}
	
	public Pattern2Box(String id,Pattern p_s){
		this.id = id;
		this.p_s = p_s;
	}
	
	public Pattern getP_s() {
		return p_s;
	}
	public void setP_s(Pattern p_s) {
		this.p_s = p_s;
	}
	public Pattern getP_e() {
		return p_e;
	}
	public void setP_e(Pattern p_e) {
		this.p_e = p_e;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
