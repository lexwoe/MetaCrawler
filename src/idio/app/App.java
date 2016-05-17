package idio.partyApp;

import java.util.ArrayList;

import idio.beans.Pattern2Box;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 *
 * All of Apps are developed based on the builtin classes. 
 *
 * @version 16.05
 */

public class App <O> {
	
	// seeds
	private ArrayList<String> seeds;
	
	// PMHC
	private Parser parser;
	private Model model;
	private Handler<O> handler;
	private Crawler<O> crawler;

	// pattern for Parser and Model
	private Pattern2Box[] pat4parser;
	private Pattern2Box[] pat4model;
	
	// boxes
	private ArrayList<O> boxes;
	
	public App(){
 
	}
	
	// run
	public void run() throws Exception{
		
	}
	
	/**
	 * flow
	 * 
	 * this flow, i think, may be good enough, no need overriding
	 * 
	 * @throws Exception
	 */
	public void flow() throws Exception{
		this.init();
		this.parser.loadPattern(this.pat4parser);
		this.model.loadPattern(this.pat4model);
		this.handler.setParser(this.parser);
		this.handler.setModel(this.model);
		this.crawler.setHandler(this.handler);
		this.crawler.setSeeds(seeds);
		this.crawler.run();
		this.over();
	}
	
	// initiate, act in start of flow
	public void init() throws Exception{
		
	}
	
	// over, act in end of flow
	public void over() throws Exception{
			
	}
		
	public ArrayList<O> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<O> boxes) {
		this.boxes = boxes;
	}

	public ArrayList<String> getSeeds() {
		return seeds;
	}

	public void setSeeds(ArrayList<String> seeds) {
		this.seeds = seeds;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Handler<O> getHandler() {
		return handler;
	}

	public void setHandler(Handler<O> handler) {
		this.handler = handler;
	}

	public Crawler<O> getCrawler() {
		return crawler;
	}

	public void setCrawler(Crawler<O> crawler) {
		this.crawler = crawler;
	}

	public Pattern2Box[] getPat4parser() {
		return pat4parser;
	}

	public void setPat4parser(Pattern2Box[] pat4parser) {
		this.pat4parser = pat4parser;
	}

	public Pattern2Box[] getPat4model() {
		return pat4model;
	}

	public void setPat4model(Pattern2Box[] pat4model) {
		this.pat4model = pat4model;
	}
	
}
