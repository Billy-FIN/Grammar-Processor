/**
* GrammarSolver Class is used for manipulating a grammar.
* <p>
* This class was written for Programming Assignments 8
* in COSI 12B
* 
* @author Qiuyang Wang
* @version .8/13/2022
 */
import java.util.*;

public class GrammarSolver {
	//fields
	/**
	 * Used for storing terminals, non-terminals, and rules
	 */
	private Map<String, String[][]> grammarRules = new TreeMap<String, String[][]>();
	
	
	//constructor
	/**
	 * Constructs a new GrammarSolcer object which can
	 * store a list in a Map.
	 * 
	 * @param rules List&lt;String&gt;  
	 * @exception IllegalArgumentException Throws a IllegalArgumentException if the list is null
	 *                                     or same non-terminal are detected.
	 */
	public GrammarSolver(List<String> rules) {
		if(rules == null || rules.isEmpty()) {
			throw new IllegalArgumentException("The List cannot be null!");
		}
		for (String lines: rules) {
			//separates the content by checking "::=" and obtain non-terminal
			String[] pieces = lines.split("::=");
			String nonTerminal = pieces[0].trim();
			pieces[1] = pieces[1].trim();
			if (grammarRules.containsKey(nonTerminal)) {
				throw new IllegalArgumentException("Same non-terminal found!");
			}
			//deals with 2+ rules
			if (pieces[1].contains("|")) {
				String[] multiRules = pieces[1].split("[|]");
				String[][] allRules = new String[multiRules.length][];
				for (int i = 0; i < allRules.length; i++) {
					allRules[i] = multiRules[i].trim().split("[ \t]+");
				}
				grammarRules.put(nonTerminal, allRules);
			//deals with only one rule	
			} else {
				String[][] allRules = new String[1][];
				allRules[0] = pieces[1].split("[ \t]+");
				grammarRules.put(nonTerminal, allRules);
			}
		}
	}
	
	//methods
	/**
	 * Verifies that the Map contains the given key.
	 * 
	 * @param symbol non-terminals.
	 * @return boolean true or false.
	 */
	public boolean contains(String symbol) {
		return grammarRules.containsKey(symbol);
	}
	
	/**
	 * Obtains all the key of this GrammerSolver object's Map.
	 * 
	 * @return Set&lt;String&gt; return as a set data type with all the keys in it.
	 */
	public Set<String> getSymbols() {
		return grammarRules.keySet();
	}
	
	/**
	 * Operates the algorithm that can generate a random occurrence of
	 * the given symbol. 
	 * 
	 * @param symbol String a terminal or a non-terminal.
	 * @return String the output sentence or word after operating this method.
	 * @exception IllegalArgumentException Throws a IllegalArgumentException if the symbol is null or empty.
	 */
	public String generate(String symbol) {
		if (symbol == null || symbol.length() == 0) {
			throw new IllegalArgumentException("Invalid symbol!");
		}
		String output = "";
		//recursive case
		if (grammarRules.containsKey(symbol)) {
			Random random = new Random();
			String[][] rules = grammarRules.get(symbol);
			int chooseRule = random.nextInt(rules.length);		//randomly selection
			for (String nextSymbol: rules[chooseRule]) {
				output += " " + generate(nextSymbol.trim());
			}
			return output.trim();
		} else {
			//base case
			return symbol.trim();
		}
	}
	
	/**
	 * (This method is just for JunitTest purpose)
	 * Obtains this GrammerSolver object's Map.
	 * 
	 * @return Map&lt;String, String[][]&gt; return the whole Map.
	 */
	public Map<String, String[][]> getValue() {
		return grammarRules;
	}
}
