package idio.crawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import idio.handler.Handler;
import idio.handler.HandlerExtend;

/**
 * 
 * @author lexwoe
 *
 * This class is used for gathering all of resources for
 * crawling web pages. 
 *
 * @param <O> : Any Object, e.g. Box, even String etc.
 * 
 * @version 16.05
 */

public class Crawler<O> {

	private List<String> seeds;
	private Handler<O> hd;
	private ArrayList<O> boxes;

	public Crawler() {

	}

	public Crawler(List<String> seeds) {
		this.seeds = seeds;
	}

	public Crawler(HandlerExtend<O> hd) {
		this.hd = hd;
	}

	public Crawler(List<String> seeds, Handler<O> hd) {
		this.seeds = seeds;
		this.hd = hd;
	}

	/**
	 * Visit all of seeds
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		this.boxes = new ArrayList<O>();
		for (String url : this.seeds) {
			if (touch(new URL(url)))
				this.visit(new URL(url));
		}
	}

	/**
	 * Go to seed
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void visit(URL url) throws Exception {
		this.hd.handleURL(url);
		if (this.hd.getBoxes() != null)
			this.addBox(this.hd.getBoxes());
	}

	/**
	 * Should visit ? 
	 * Or give an action here
	 * 
	 * @param url
	 * @return
	 */
	public boolean touch(URL url) {
		return true;
	}

	/**
	 * Add results ## you can override ##
	 * 
	 * @return
	 */
	public void addBox(ArrayList<O> boxes) {
		this.boxes.addAll(boxes);
	}

	public List<String> getSeeds() {
		return seeds;
	}

	public void setSeeds(List<String> seeds) {
		this.seeds = seeds;
	}

	public ArrayList<O> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<O> boxes) {
		this.boxes = boxes;
	}

	public Handler<O> getHandler() {
		return hd;
	}

	public void setHandler(Handler<O> hd) {
		this.hd = hd;
	}

}
