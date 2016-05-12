package idio.tests;

import java.util.ArrayList;

import idio.app.App;
import idio.app.App4Simple;
import idio.boxes.BoxExample;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.handler.HandlerExtend;
import idio.model.Model;
import idio.parser.Parser;

public class TEST_App4Simple {

	public static void main(String[] args) throws Exception {

		Parser p = new Parser();
		Model m = new Model();
		Handler<BoxExample> h = new HandlerExtend<BoxExample>("idio.boxes.BoxExample");
		Crawler<BoxExample> c = new Crawler<BoxExample>();
		
		
		ArrayList<String> seeds = new ArrayList<String>();
		seeds.add("http://news.bioon.com/Biologicalinformation/list-2.html");
		seeds.add("http://toutiao.io/subjects/4");
		App<BoxExample> app = new App4Simple<BoxExample>(p,m,h,c);
		app.setSeeds(seeds);
		ArrayList<BoxExample> b4c = app.app();
		for (int i = 0; i < b4c.size(); i++) {
			System.out.println(b4c.get(i).getLink());
			System.out.println(b4c.get(i).getTitle());
		}
		
	}

}
