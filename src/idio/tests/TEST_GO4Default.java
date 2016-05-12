package idio.tests;

import java.util.ArrayList;

import idio.beans.Pattern2Box;
import idio.boxes.BoxDefault;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.handler.HandlerDefault;
import idio.handler.HandlerExtend;
import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 * 
 * Test for Default
 * 
 * @version 16.05
 *
 */

public class TEST_GO4Default {

	public static void main(String[] args) throws Exception {

		// Step 1. Specify a Parser, adding some patterns
		Parser p = new Parser();
		p.loadPattern(
				new Pattern2Box[] { new Pattern2Box(HandlerExtend.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
						new Pattern2Box(HandlerExtend.BOX_TITLE, Parser.HTML_H34_L),
						new Pattern2Box(HandlerExtend.BOX_LINK, Parser.HTML_A_L),
						new Pattern2Box(HandlerExtend.BOX_IMG, Parser.HTML_I_L) });

		// Step 2. Specify a Model, adding some patterns
		Model m = new Model();
		m.loadPattern(new Pattern2Box[] { new Pattern2Box(null, Parser.HTML_H34_S, Parser.HTML_D_E) });

		// Step 3. Create a Handler for gathering Parser and Model, with
		// specifying a class for storing results
		Handler<BoxDefault> h = new HandlerDefault(p, m);

		// Step 4. Get some seeds for crawling
		ArrayList<String> seeds = new ArrayList<String>();
		seeds.add("http://news.bioon.com/Biologicalinformation/list-2.html");
		seeds.add("http://toutiao.io/subjects/4");

		// Step 5. Build a Crawler, and run it
		Crawler<BoxDefault> c = new Crawler<BoxDefault>(seeds, h);
		c.run();

		// Step 6. See the results
		ArrayList<BoxDefault> b4c = c.getBoxes();
		for (int i = 0; i < b4c.size(); i++) {
			System.out.println(b4c.get(i).getSess().get("Link"));
			System.out.println(b4c.get(i).getSess().get("Title"));
		}

	}

}
