package idio.tests;

import idio.boxes.BoxDefault;
import idio.partyApp.App;
import idio.partyApp.AppDefault;
import idio.utils.PatternFactory;

public class TEST_App4Default {

	public static void main(String[] args) throws Exception {

		App<BoxDefault> app = new AppDefault("libs/urls.mc", PatternFactory.APP_SIMPLE_P_PAT,
				PatternFactory.APP_SIMPLE_M_PAT, 1);
		app.run();

	}

}
