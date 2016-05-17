package idio.model;

import java.util.regex.Matcher;

/**
 * 
 * @author lexwoe
 *
 * locate target section
 *
 * @version 16.05
 */

public class ModelAdvance extends Model {

	// allow targeting or not
	private boolean flag = false;
	
	private int sindex;
	private int eindex = -100;
	
	
	/**
	 * detect target section
	 * @param line
	 * @return
	 */
	public boolean detect(String line){
		if(super.getPats()!=null){
			// detect starting marks of target
			if (this.detectStart(line)) {
				this.flag = true;
				return true;
			// detect ending marks of target
			} else if (this.detectEnd(line))
				this.flag = false;
		}
		return false;
	}
	
	public boolean detectStart(String line){
		if(sindex<super.getPats().length){
			Matcher m = super.getPats()[sindex].getP_s().matcher(line);
			if(m.find())
				sindex ++;
		}
		else{
			sindex = 0;
			eindex = super.getPats().length;
			return true;
		}
		return false;
	}
	
	public boolean detectEnd(String line){
		if(eindex==-100)
			return false;
		if(eindex>-1){
			Matcher m = super.getPats()[eindex].getP_e().matcher(line);
			if(m.find())
				eindex --;
		}else{
			eindex = super.getPats().length;
			sindex = 0;
			return true;
		}
		return false;
	}

	public boolean allow() {
		return flag;
	}
	
}
