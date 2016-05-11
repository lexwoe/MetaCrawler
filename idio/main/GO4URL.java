package idio.main;

import java.util.ArrayList;

import idio.beans.Pattern2Box;
import idio.crawler.Crawler;
import idio.handler.Handler;
import idio.handler.HandlerURL;
import idio.parser.Parser;

public class GO4URL {
	
	public static void main(String[] args) throws Exception {
		
		// Step 1. Specify a Parser, adding some patterns
		Parser p = new Parser();
		p.loadPattern(new Pattern2Box[]{
				new Pattern2Box(Handler.BOX_LINK,Parser.HTML_A_L),
				new Pattern2Box(Handler.BOX_LINK,Parser.HTML_I_L),
		});
		
		// Step 2. Create a Handler
		Handler<String> h = new HandlerURL(p);
		
		// Step 3. Get some seeds
		ArrayList<String> seeds = new ArrayList<String>();
		seeds.add("http://news.bioon.com/Biologicalinformation/list-2.html");
		seeds.add("http://toutiao.io/subjects/4");
		
		// Step 4. Build a Crawler, and run it
		Crawler<String> c = new Crawler<String>(seeds,h);
		c.run();
		
		// Step 5. See the results
		ArrayList<String> b4c = c.getBoxes();
		for(int i=0;i<b4c.size();i++){
			System.out.println(b4c.get(i));
		}
	
	}

}
