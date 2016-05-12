package idio.boxes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lexwoe
 *
 * This is the default box for storing information
 *
 * @version 16.05
 * 
 */

public class BoxDefault {

	// ID for the Box, optional
	private String id;
	// used for displaying all of values
	private String[] keys ;
	// store all of values
	private Map<String,String> sess = new HashMap<String,String>();
	// remark, optional
	private String remark;
	
	// add values
	public void addSess(String k,String v){
		this.sess.put(k, v);
	}
	
	// display values
	public String[] sess2Array(){
		String[] strs = new String[this.keys.length];
		for(int i=0;i<this.keys.length;i++)
			strs[i] = sess.get(this.keys[i]);
		return strs;
	}
	
	// display values
	public String[] sess2Array(String[] keys){
		String[] strs = new String[keys.length];
		for(int i=0;i<keys.length;i++)
			strs[i] = sess.get(keys[i]);
		return strs;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public Map<String, String> getSess() {
		return sess;
	}
	public void setSess(Map<String, String> sess) {
		this.sess = sess;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
