package idio.tests;

import java.util.ArrayList;

import idio.beans.Pattern2Box;
import idio.boxes.BoxExample;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.handler.HandlerExtend;
import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 * 
 * Test for Extend
 * 
 * @version 16.05
 *
 */

public class TEST_GO4Extend {

	public static void main(String[] args) throws Exception {
		
		// Step 1. Specify a Parser, adding some patterns
		Parser p = new Parser();
		p.loadPattern(new Pattern2Box[] { new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
				new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_L),
				new Pattern2Box(Handler.BOX_LINK, Parser.HTML_A_L),
				new Pattern2Box(Handler.BOX_IMG, Parser.HTML_I_L) });

		// Step 2. Specify a Model, adding some patterns
		Model m = new Model();
		m.loadPattern(new Pattern2Box[] { new Pattern2Box(null, Parser.HTML_H34_S, Parser.HTML_D_E) });

		// Step 3. Create a Handler for gathering Parser and Model, with specifying a class for storing results
		Handler<BoxExample> h = new HandlerExtend<BoxExample>("idio.boxes.BoxExample", p, m);

		// Step 4. Get some seeds for crawling
		ArrayList<String> seeds = new ArrayList<String>();
		seeds.add("http://news.bioon.com/Biologicalinformation/list-2.html");
		seeds.add("http://toutiao.io/subjects/4");

		// Step 5. Build a Crawler, and run it
		Crawler<BoxExample> c = new Crawler<BoxExample>(seeds, h);
		c.run();

		// Step 6. See the results
		ArrayList<BoxExample> b4c = c.getBoxes();
		for (int i = 0; i < b4c.size(); i++) {
			System.out.println(b4c.get(i).getLink());
			System.out.println(b4c.get(i).getTitle());
		}
	}

}
