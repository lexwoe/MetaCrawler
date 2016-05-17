package idio.model;

/**
 * 
 * @author lexwoe
 *
 * locate target section
 *
 * @version 16.05
 */

public class ModelSimple extends Model {

	// allow targeting or not
	private boolean flag = false;
	
	/**
	 * detect target section
	 * @param line
	 * @return
	 */
	public boolean detect(String line){
		if(super.getPats()!=null){
			// detect starting marks of target
			if (super.getPats()[0].getP_s()!=null && super.getPats()[0].getP_s().matcher(line).find()) {
				this.flag = true;
				return true;
			// detect ending marks of target
			} else if (super.getPats()[0].getP_e()!=null && super.getPats()[0].getP_e().matcher(line).find())
				this.flag = false;
		}
		return false;
	}

	public boolean allow() {
		return flag;
	}

	public void setAllow(boolean flag) {
		this.flag = flag;
	}
	
	
}
