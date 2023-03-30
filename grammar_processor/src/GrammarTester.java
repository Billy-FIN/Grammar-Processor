/**
  * This Class provides some example methods to test the main functioning of PA8
  * COSI 12B, Brandeis University
  * 
  * @author Qiuyang Wang
  * @version 08/13/2022
  */


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrammarTester {

	/**
	 * Tests GrammarSolver Class
	 */
	private GrammarSolver s;
	
	
	@BeforeEach
	/**
	 * Creates a GrammarSolver for testing
	 * Run before every test
	 */
	private void setup() {
		ArrayList<String> rules = new ArrayList<>();
		rules.add("<sen>::= <s> <v>|<s> <v> <p>|<s> <v> <o>");
		rules.add("<s>::= <att> <n>|<n> ");
		rules.add("<v>::= <v>|<adv> <v>");
		rules.add("<o>::= <n>|<com> <n>");
		rules.add("<p>::= nice|ridiculous|soon|friendly|sick|white|president|damp|large");
		rules.add("<att>::=pale|gorgeous");
		rules.add("<adv>::=towards|fully|never|very|seldom");
		rules.add("<com>::=beautiful|awful");
		rules.add("<n>::=man|Chicago|table|happiness|justice");
		rules.add("<hh>::=<h> <h>");
		rules.add(" <h>::=ohhhhh");
		rules.add("<xx>::= <x>");
		rules.add("<x>::=hahaha");
		rules.add("<xxx>::=<h>| <x>");
		s = new GrammarSolver(rules);
	}

	@Test
	/**
	 * Checks the correctness of terminals while in the Map
	 */
	public void testTerminal() {
		//verify that terminal "friendly" is at the right place
		assertEquals("friendly",s.getValue().get("<p>")[3][0]);
	}

	@Test
	/**
	 * Checks generate method when there are more spaces in the file
	 * Makes sure generate method throws an exception if given null or empty string
	 */
	public void testSpaceInGenerate() throws FileNotFoundException {
		//verify that generating when there is a space in the very front of rules
		assertEquals("hahaha",s.generate("<x>"));
		//verify that generating when there is a space in the very front of each line
		assertEquals("ohhhhh", s.generate("<h>"));
		//verify that each rule might have surrounding spaces around it
		String sentence=s.generate("<xxx>");
		boolean equalsoh=sentence.equals("ohhhhh");
		boolean equalshahaha=sentence.equals("hahaha");
		assertTrue(equalsoh || equalshahaha,"picking from OR test");
	}
}
