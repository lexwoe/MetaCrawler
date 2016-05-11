package idio.app;

import java.util.ArrayList;

import idio.beans.Pattern2Box;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.model.Model;
import idio.parser.Parser;

public class App <O>{

	public static final Pattern2Box[] APP_SIMPLE_P_PAT = new Pattern2Box[]{
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_L),
			new Pattern2Box(Handler.BOX_LINK, Parser.HTML_A_L)
	};
	public static final Pattern2Box[] APP_ADVANCE_P_PAT = new Pattern2Box[]{
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_L),
			new Pattern2Box(Handler.BOX_LINK, Parser.HTML_A_L),
			new Pattern2Box(Handler.BOX_IMG, Parser.HTML_I_L),
			new Pattern2Box(Handler.BOX_IMG, Parser.HTML_I_L),
			new Pattern2Box(Handler.BOX_CNT, Parser.HTML_P_S, Parser.HTML_P_E),
			new Pattern2Box(Handler.BOX_CNT, Parser.HTML_P_L)
	};
	public static final Pattern2Box[] APP_SIMPLE_M_PAT = new Pattern2Box[]{
			new Pattern2Box(null, Parser.HTML_H34_S, Parser.HTML_D_E)
	};
	
	private ArrayList<String> seeds;
	private Parser parser;
	private Model model;
	private Handler<O> handler;
	private Crawler<O> crawler;
	
	private Pattern2Box[] pat4parser;
	private Pattern2Box[] pat4model;
	
	public App(){
		
	}
	
	public App(Parser p,Model m,Handler<O> h,Crawler<O> c){
		this.parser = p;
		this.model = m;
		this.handler = h;
		this.crawler = c;
	}
	
	public ArrayList<O> app() throws Exception{
		this.parser.loadPattern(this.pat4parser);
		this.model.loadPattern(this.pat4model);
		this.handler.setParser(this.parser);
		this.handler.setModel(this.model);
		this.crawler.setHandler(this.handler);
		this.crawler.setSeeds(seeds);
		this.crawler.run();
		return this.crawler.getBoxes();
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
