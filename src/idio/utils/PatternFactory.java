package idio.utils;

import idio.beans.Pattern2Box;
import idio.handler.Handler;
import idio.parser.Parser;

public class PatternFactory {
	
	/**
	 * Some patterns for Parser and Model
	 */
	
	// parsing Title and Link
	public static final Pattern2Box[] APP_SIMPLE_P_PAT = new Pattern2Box[]{
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_L),
			new Pattern2Box(Handler.BOX_LINK, Parser.HTML_A_L)
	};
	
	// parsing Title, Link, Image and Content
	public static final Pattern2Box[] APP_ADVANCE_P_PAT = new Pattern2Box[]{
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_S, Parser.HTML_H34_E),
			new Pattern2Box(Handler.BOX_TITLE, Parser.HTML_H34_L),
			new Pattern2Box(Handler.BOX_LINK, Parser.HTML_A_L),
			new Pattern2Box(Handler.BOX_IMG, Parser.HTML_I_L),
			new Pattern2Box(Handler.BOX_IMG, Parser.HTML_I_L),
			new Pattern2Box(Handler.BOX_CNT, Parser.HTML_P_S, Parser.HTML_P_E),
			new Pattern2Box(Handler.BOX_CNT, Parser.HTML_P_L)
	};
	
	// modeling
	public static final Pattern2Box[] APP_SIMPLE_M_PAT = new Pattern2Box[]{
			new Pattern2Box(null, Parser.HTML_H34_S, Parser.HTML_D_E)
	};
	
	
}
