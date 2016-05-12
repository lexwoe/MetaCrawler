package idio.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import idio.model.Model;
import idio.parser.Parser;

/**
 * 
 * @author lexwoe
 * 
 * Handle with single web page and map the results to <O> object. The
 * process is assisted by Model and Parser which are built for locating
 * and extracting targets more easily.
 *
 * @param <O> : Any Object, e.g. Box, even String etc.
 * 
 * @version 16.05
 * 
 */

public class HandlerExtend<O> extends Handler<O> {
	
	private ArrayList<O> boxes;

	// specify a class (e.g. idio.beans.Box) for storing parsing results
	private String boxname;

	// parse page
	private Parser parser;

	// locate target
	private Model model;
	
	// charset
	private String charset = "UTF-8";
	
	public HandlerExtend(String boxname){
		this.boxname = boxname;
	}

	public HandlerExtend(String boxname, Parser parser, Model model) {
		this.boxname = boxname;
		this.parser = parser;
		this.model = model;
	}
	
	public HandlerExtend(String boxname, Parser parser, Model model, String charset){
		this.boxname = boxname;
		this.parser = parser;
		this.model = model;
		this.charset = charset;
	}

	/**
	 * parse web page
	 * 
	 * First load web page content line by line, then use Model to locate the
	 * target section. After that, apply the Parser to the line and extract the
	 * content. All contents are stored into <O> Object orderly according to
	 * mapping "id" from Pattern2Box to variables in Box.
	 * 
	 * @param url
	 *            : path of web page
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void handleURL(URL url) throws Exception {

		// initiate boxes
		this.boxes = new ArrayList<O>();
		O bx = null;

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
				bx = (O) Class.forName(boxname).newInstance();
			}

			// if allowed to parsing this section, just do it
			if (this.model == null || this.model.allow()) {
				// parse line
				ArrayList<String[]> values = this.parser.parse(line);

				if (values != null) {
					for (int i = 0; i < values.size(); i++) {

						// value[id,content]
						String[] value = values.get(i);

						// use reflection to set values into <O> Object according to "id" of Pattern2Box
						String field = (new StringBuilder()).append(Character.toUpperCase(value[0].charAt(0)))
								.append(value[0].substring(1)).toString();
						// if the content is related to URL, then manage it more carefully
						if (field.equals(Handler.BOX_LINK) || field.equals(Handler.BOX_IMG)) {
							// for a sub path
							if (!value[1].startsWith("http") && !value[1].startsWith("www"))
								value[1] = url.getProtocol() + "://" + url.getHost() + value[1];
						} else if (field.equals(Handler.BOX_CNT)) {
							// for Content, not setting but appending
							String v = (String) bx.getClass().getDeclaredMethod("get" + field).invoke(bx);
							if (v != null)
								value[1] = v + "\n" + value[1];
						}
						bx.getClass().getDeclaredMethod("set" + field, String.class).invoke(bx, value[1]);
					}
				}
			}
		}
		br.close();
		is.close();
	}

	public ArrayList<O> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<O> boxes) {
		this.boxes = boxes;
	}

	public String getBoxname() {
		return boxname;
	}

	public void setBoxname(String boxname) {
		this.boxname = boxname;
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
