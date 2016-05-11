package idio.handler;

import java.net.URL;
import java.util.ArrayList;

/**
 * 
 * @author lexwoe
 *
 * Handle with single web page and map the results to <O> object
 *
 * @param <O> : Any Object, e.g. Box, even String etc.
 * 
 * @version 16.05
 * 
 */

public class Handler<O> {

	/**
	 * mapping values of Box
	 */
	public static final String BOX_TITLE = "Title";
	public static final String BOX_HEADER = "Header";
	public static final String BOX_LINK = "Link";
	public static final String BOX_IMG = "Imgurl";
	public static final String BOX_CNT = "Content";
	public static final String BOX_USER = "User";
	public static final String BOX_TIME = "Time";
	public static final String BOX_RMK = "Remark";

	private ArrayList<O> boxes;

	/**
	 * parse web page
	 * 
	 * @param url : path of web page
	 * @throws Exception
	 */
	public void handleURL(URL url) throws Exception {
		// nothing
	}

	/**
	 * results
	 * 
	 * @return results
	 */
	public ArrayList<O> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<O> boxes) {
		this.boxes = boxes;
	}
}
