package idio.parser;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idio.beans.Pattern2Box;

/**
 * 
 * @author lexwoe
 *
 * parse line and extract information
 *
 * @version 16.05
 */

public class Parser {

	/**
	 * some patterns for extracting targets
	 */
	public static final Pattern HTML_H_S = Pattern.compile("<h\\d+\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_Sg = Pattern.compile("<h\\d+\\s*.*>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_E = Pattern.compile("(.*)</h\\d+>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_Eg = Pattern.compile("(.*?)(</.*>)?</h\\d+>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_L = Pattern.compile("<h\\d+\\s*.*?>(.*)</h\\d+>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H_Lg = Pattern.compile("<h\\d+\\s*.*>(.*)(</.*>)?</h\\d+>",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H34_S = Pattern.compile("<h[34]\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H34_E = Pattern.compile("(.*)</h[34]>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_H34_L = Pattern.compile("<h[34]\\s*.*?>(.*)</h[34]>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_S = Pattern.compile("<p\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_Sg = Pattern.compile("<p\\s*.*>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_E = Pattern.compile("(.*)</p>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_Eg = Pattern.compile("(.*?)(</.*>)?</p>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_L = Pattern.compile("<p\\s*.*?>(.*)</p>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_P_Lg = Pattern.compile("<p\\s*.*>(.*?)(</.*>)?</p>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_A_L = Pattern.compile("<a\\s+.*href=[\"'](.*?)[\"'].*?>",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_S = Pattern.compile("<div\\s*.*?>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_Sg = Pattern.compile("<div .*>(.*)", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_E = Pattern.compile("(.*)</div>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_D_Eg = Pattern.compile("(.*?)(</.*>)?</div>", Pattern.CASE_INSENSITIVE);
	public static final Pattern HTML_I_L = Pattern.compile("<img\\s+.*src=[\"'](.*?)[\"'].*?>",
			Pattern.CASE_INSENSITIVE);
	
	public static final Pattern LINE_FILE = Pattern.compile(
			".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$",Pattern.CASE_INSENSITIVE);
	public static final Pattern LINE_IMAGE = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$",Pattern.CASE_INSENSITIVE);
	public static final Pattern LINE_SPACE = Pattern.compile("^\\s*$");
	public static final Pattern LINE_TAG = Pattern.compile("<.*>(.*)</.*>");

	// within pair of tags
	private Stack<String> tags = new Stack<String>();
	
	// patterns
	private Pattern2Box[] pats;

	public void loadPattern(Pattern2Box[] pats){
		this.pats = pats;
	}

	/**
	 * extract information according to patterns
	 * 
	 * @param line
	 * @return
	 */
	public ArrayList<String[]> parse(String line) {

		ArrayList<String[]> al = new ArrayList<String[]>();

		if (this.pats != null) {
			
			// running patterns
			String tmpLine = line;
			ArrayList<String[]> tips = new ArrayList<String[]>();
			for (Pattern2Box p : this.pats) {
				// single pattern
				if (p.getP_e() == null) {
					Matcher m = p.getP_s().matcher(line);
					if (m.find()) {
						tmpLine = m.group(1);
						// removing other tags
						m = LINE_TAG.matcher(tmpLine);
						if (m.find())
							tmpLine = m.group(1);
						// record id and content
						tips.add(new String[] { p.getId(), tmpLine });
					}
				// paired pattern
				} else {
					tmpLine = line;
					Matcher m = p.getP_s().matcher(tmpLine);
					
					// starting tag extracting
					if (m.find()) {
						this.tags.push(p.getId());
						tmpLine = m.group(1);
					}
					m = p.getP_e().matcher(tmpLine);
					
					// ending tag extracting
					if (m.find()) {
						if (this.tags != null && this.tags.size() > 0 && ((String) this.tags.peek()).equals(p.getId()))
							this.tags.pop();
						else
							System.out.println("[ ERROR ] : NOT MATCH " + p.getId());
						tmpLine = m.group(1);
					}

					if (!tmpLine.equals(line)) {
						m = LINE_TAG.matcher(tmpLine);
						if (m.find())
							tmpLine = m.group(1);
						tips.add(new String[] { p.getId(), tmpLine });
					}
				}
			}

			// return pattern results
			if (tips.size() > 0) {
				for (int i = 0; i < tips.size(); i++) {
					String[] terms = tips.get(i);
					// if content consists of blank, skip
					if (LINE_SPACE.matcher(terms[1]).find())
						continue;
					al.add(terms);
				}
			}

			// any content (not blank) within pair of tags, add
			if (this.tags.size() > 0 && !LINE_SPACE.matcher(line).find()) {
				tmpLine = line;
				Matcher m = LINE_TAG.matcher(line);
				if (m.find())
					tmpLine = m.group(1);
				al.add(new String[] { (String) this.tags.peek(), tmpLine });
			}
		}

		return al;
	}
}
