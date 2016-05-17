package idio.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import idio.beans.Pattern2Box;
import idio.boxes.BoxDefault;
import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 * 
 * Handle with single web page and map the results to BoxDefault. The
 * process is assisted by Model and Parser which are built for locating
 * and extracting targets more easily.
 *
 * @param <O> : Any Object, e.g. Box, even String etc.
 * 
 * @version 16.05
 * 
 */

public class HandlerDefault extends Handler<BoxDefault> {

	private ArrayList<BoxDefault> boxes;

	// parse page
	private Parser parser;

	// locate target
	private Model model;
	
	// charset
	private String charset = "UTF-8";
	
	public HandlerDefault(){
		
	}
	
	public HandlerDefault(Parser parser, Model model) {
		this.parser = parser;
		this.model = model;
	}
	
	public HandlerDefault(Parser parser, Model model, String charset){
		this.parser = parser;
		this.model = model;
		this.charset = charset;
	}

	/**
	 * parse web page
	 * 
	 * First load web page content line by line, then use Model to locate the
	 * target section. After that, apply the Parser to the line and extract the
	 * content. All contents are stored into BoxDefault according to mapping 
	 * "id" from Pattern2Box to Sess in BoxDefault.
	 * 
	 * @param url
	 *            : path of web page
	 * 
	 */
	public void handleURL(URL url) throws Exception {

		// initiate boxes
		this.boxes = new ArrayList<BoxDefault>();
		BoxDefault bx = null;
		
		Set<String> keys = new HashSet<String>();
		for(Pattern2Box p2b:this.parser.getPats()){
			keys.add(p2b.getId());
		}
		String[] ks = keys.toArray(new String[1]);

		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,this.charset));
		String line = null;
		while ((line = br.readLine()) != null) {

			// locate target section
			if (this.model == null || this.model.detect(line)) {

				// find a new target section, thus, store the last box
				if (bx != null) {
					boxes.add(bx);
				}

				// initiate the box according to the class specified
				bx = new BoxDefault();
				// set all of IDs
				bx.setKeys(ks);
			}

			// if allowed to parsing this section, just do it
			if (this.model == null || this.model.allow()) {
				// parse line
				ArrayList<String[]> values = this.parser.parse(line);

				if (values != null) {
					for (int i = 0; i < values.size(); i++) {

						// value[id,content]
						String[] value = values.get(i);

						// if the content is related to URL, then manage it more carefully
						if (value[0].equals(Handler.BOX_LINK) || value[0].equals(Handler.BOX_IMG)) {
							// for a sub path
							if (!value[1].startsWith("http") && !value[1].startsWith("www"))
								value[1] = url.getProtocol() + "://" + url.getHost() + value[1];
						} else if (value[0].equals(Handler.BOX_CNT)) {
							// for Content, not setting but appending
							value[1] = bx.getSess().get(value[0])==null?value[1]:(bx.getSess().get(value[0])+"\n"+value[1]);
						}
						
						bx.addSess(value[0], value[1]);
					}
				}
			}
		}
		br.close();
		is.close();
	}

	public ArrayList<BoxDefault> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<BoxDefault> boxes) {
		this.boxes = boxes;
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

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	

}
