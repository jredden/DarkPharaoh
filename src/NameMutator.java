import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Generates all the aliases of the Dark Pharaoh
 * 
 * You need JDK 1.5 or greater to compile and run
 * 
 * To compile: javac NameMutator.java
 * 
 * To Run: java NameMutator
 * 
 * -- or --
 * 			java NameMutator 32767 
 * 
 * generates 32767 aliases.
 * 
 * This program only generates English aliases
 * 
 * @author jredden
 *
 */

public class NameMutator {

	private static int runCount = 10;
	private static Map<Character, Boolean> cMap = new HashMap<Character, Boolean>();
	static {
		cMap.put('N', false);
		cMap.put('Y', false);
		cMap.put('A', false);
		cMap.put('R', false);
		cMap.put('L', false);
		cMap.put('a', false);
		cMap.put('T', false);
		cMap.put('H', false);
		cMap.put('O', false);
		cMap.put('t', false);
		cMap.put('E', false);
		cMap.put('P', false);
	}

	private static Map<String, Integer> genedName;

	private static void clear() {
		Set<Character> keySet = cMap.keySet();
		Iterator<Character> iter = keySet.iterator();
		while (iter.hasNext()) {
			cMap.put(iter.next(), false);
		}
	}

	private static Character drawALetter() {
		Character character = null;
		Boolean foundOne = false;
		Set<Character> keySet = cMap.keySet();
		while (!foundOne) {
			int position = (int) (Math.random() * cMap.size());
			// System.out.println("position:"+position);
			Iterator<Character> iter = keySet.iterator();
			int count = 0;
			while (iter.hasNext()) {
				Character ckey = iter.next();
				if (cMap.get(ckey) == false && count == position) {
					character = ckey;
					foundOne = true;
					cMap.put(ckey, true);
					break;
				}
				++count;
			}
		}
		return character;
	}

	private static Boolean isDrawDone() {
		Boolean anser = true;
		Set<Character> keySet = cMap.keySet();
		Iterator<Character> iter = keySet.iterator();
		while (iter.hasNext()) {
			if (cMap.get(iter.next()) == false) {
				anser = false;
				break;
			}
		}
		return anser;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 0) {
			runCount = Integer.parseInt(args[0]);
		}
		genedName = new HashMap<String, Integer>();
		for (int iter = 0; iter < runCount;) {
			StringBuffer sbuf = new StringBuffer();
			clear();
			while (!isDrawDone()) {
				sbuf.append(drawALetter());
			}
			String outstring = sbuf.toString().toUpperCase();
			if (!genedName.containsKey(outstring)) {
				System.out.println(outstring);
				genedName.put(outstring, iter);
				++iter;
			} else {
				System.out.println(outstring + " is already there");
			}
		}

	}

}
