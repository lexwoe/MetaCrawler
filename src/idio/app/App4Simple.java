package idio.app;

import idio.beans.Pattern2Box;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.model.Model;
import idio.parser.Parser;

public class App4Simple<O> extends App<O>{

	private Pattern2Box[] pat4parser = App.APP_SIMPLE_P_PAT;
	private Pattern2Box[] pat4model = App.APP_SIMPLE_M_PAT; 
	
	public App4Simple(Parser p,Model m,Handler<O> h,Crawler<O> c){
		super(p,m,h,c);
		super.setPat4model(this.pat4model);
		super.setPat4parser(this.pat4parser);
	}

	public void setPat4parser(Pattern2Box[] pat4parser) {
		super.setPat4parser(pat4parser);
	}

	public void setPat4model(Pattern2Box[] pat4model) {
		super.setPat4model(pat4model);
	}
	
	
}
