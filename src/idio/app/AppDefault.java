package idio.partyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idio.beans.Pattern2Box;
import idio.boxes.BoxDefault;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 *
 * All of Apps are developed based on the builtin classes. By default,
 * and for convenience, we only take BoxDefault as a standard 
 *
 * @version 16.05
 */

public class AppDefault extends App<BoxDefault>{
	
	// this is used for mapping URLs for crawling news 
	public static final Pattern APP_PAT_URL = Pattern.compile("(.*)\\{\\?\\}(.*)");
	
	// pref and suff derived from splitting URLs
	private String pref;
	private String suff;
	
	// how many pages you want to deep in ?
	private int depth;
	
	// name of Parser, Model, Handler and Crawler
	private String p;
	private String m;
	private String h;
	private String c;
	
	// seeds
	private ArrayList<String> seeds;
	
	// people upload their favorite URLs with specified PMHCs
	// of course, you can also type a long remark as you like ~
	private ArrayList<String[]> terms;
	
	// storing boxes
	private ArrayList<BoxDefault> boxes;
	
	public AppDefault(){
		
	}

	/**
	 * 
	 * @param terms : all of specified URLs and PMHCs
	 * @param pat4parser : Pattern of Parser
	 * @param pat4model : Pattern of Model
	 * @param depth : you know it
	 */
	public AppDefault(ArrayList<String[]> terms, Pattern2Box[] pat4parser, Pattern2Box[] pat4model, int depth){
		this.terms = terms;
		super.setPat4parser(pat4parser);
		super.setPat4model(pat4model);
		this.depth = depth;
	}
	
	/**
	 * 
	 * @param path : path of file recording URLs and PMHCs
	 * @param pat4parser
	 * @param pat4model
	 * @param depth
	 * @throws Exception
	 */
	public AppDefault(String path,Pattern2Box[] pat4parser, Pattern2Box[] pat4model, int depth) throws Exception{
		super.setPat4parser(pat4parser);
		super.setPat4model(pat4model);
		this.depth = depth;
		this.terms = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		while((line=br.readLine())!=null){
			this.terms.add(line.split("\t"));
		}
		br.close();
	}
	
	/**
	 * run all of URLs
	 */
	public void run() throws Exception{
		this.boxes = new ArrayList<BoxDefault>();
		for(int k=0;k<this.terms.size();k++){
			String[] strs = this.terms.get(k);
			Matcher matcher = AppDefault.APP_PAT_URL.matcher(strs[0]);
			if(matcher.find()){
				this.pref = matcher.group(1);
				this.suff = matcher.group(2);
			}
			this.p = "idio.parser."+strs[1];
			this.m = "idio.model."+strs[2];
			this.h = "idio.handler."+strs[3];
			this.c = "idio.crawler."+strs[4];
			this.seeds = new ArrayList<String>();
			for(int i=0;i<this.depth;i++){
				seeds.add(this.pref+(i+1)+this.suff);
			}
			super.flow();
		}
	}
	
	public void over(){
		this.boxes.addAll(super.getCrawler().getBoxes());
	}
	
	@SuppressWarnings("unchecked")
	public void init() throws Exception{
		super.setSeeds(this.seeds);
		super.setParser((Parser) Class.forName(p).newInstance());
		super.setModel((Model) Class.forName(m).newInstance());
		super.setHandler((Handler<BoxDefault>) Class.forName(h).newInstance());
		super.setCrawler((Crawler<BoxDefault>) Class.forName(c).newInstance());
	}

	public ArrayList<BoxDefault> getBoxes(){
		return this.boxes;
	}
	
	public void setBoxes(ArrayList<BoxDefault> boxes){
		this.boxes = boxes;
	}
	
}
