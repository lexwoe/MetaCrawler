package idio.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 * 
 * Handle with single web page and map the results to String. The
 * process is only assisted by Parser which is built for extracting 
 * targets more easily and flexibly.
 *
 * @version 16.05
 * 
 */

public class HandlerURL extends Handler<String> {

	private ArrayList<String> boxes;
	private Parser parser ;
	
	public HandlerURL(Parser parser){
		this.parser = parser;
	}
	
	/**
	 * parse web page
	 * 
	 * Simply read line by line, and extract all of URLs
	 * 
	 */
	public void handleURL(URL url) throws IOException {
		this.boxes = new ArrayList<String>();

		InputStream is = url.openStream(); // throws an IOException
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			ArrayList<String[]> values = this.parser.parse(line);
			// set values
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					String[] value = values.get(i);
					String field = (new StringBuilder()).append(Character.toUpperCase(value[0].charAt(0)))
							.append(value[0].substring(1)).toString();
					if (field.equals("Link")) {
						if (!value[1].startsWith("http") && !value[1].startsWith("www"))
							value[1] = url.getProtocol() + "://" + url.getHost() + value[1];
					} 
					this.boxes.add(value[1]);
				}
			}
		}
		br.close();
		is.close();
	}
	
	/**
	 * make boxes accessible
	 */
	public ArrayList<String> getBoxes(){
		return this.boxes;
	}
}
