package edu.institution.finalproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnagramEvaluatorImpl implements AnagramEvalutator {

	private List<String> anagrams = new ArrayList<String>();
	

	/*  This recursive anagram sorting implementation was influenced by the coder "dekay" on GeeksForGeeks.org.
		Link attached here: https://www.geeksforgeeks.org/print-all-permutations-of-a-string-in-java/
	 *
	 *	
	 *  At each turn in the for-loop, the sortAnagrams method scans each index of the string starting from the first character
	 *  to the last. For each letter in the word, the string value declared as "answer" populates with all of the rest of the
	 *  word with the initial character flipped to the end.  
	 *  
	 *  This describes an exponentially complex algorithm, or O(2n). 
	 */

	public void sortAnagrams(String string, String answer) {

		

		if (string.length() == 0) { // If the next string character == 0, the string scan is complete. This recursion's base case.
			anagrams.add(answer);
			return;
		}

		for (int i = 0; i < string.length(); i++) {
			char currentChar = string.charAt(i);
			String ros = string.substring(0, i) + string.substring(i + 1);

			sortAnagrams(ros, answer + currentChar);
		}
	}

	public List<String> getAnagrams() {
		List<String> noDupes = new ArrayList<String>();
		for (int i = 0; i < anagrams.size(); i++) {
			if (!noDupes.contains(anagrams.get(i))) {
				noDupes.add(anagrams.get(i));
			}
		}
		return noDupes;
	}

	public void setAnagrams(List<String> anagrams) {
		this.anagrams = anagrams;
	}

	@Override
	public List<String> evaluate(String anagram, String option) {
		sortAnagrams(anagram.toUpperCase(), "");
		List<String> anagramList = this.getAnagrams();
		if (option.equals("nf"))
			return anagramList;
		else if (option.equals("word")) {
			List<String> realWordsList = new ArrayList<String>();
			AnagramDataReaderImpl dr = new AnagramDataReaderImpl();
			Set<String> realWords = dr.readData();
			for (int i = 0; i < anagramList.size(); i++) {
				if (realWords.contains(anagramList.get(i))) {
					realWordsList.add(anagramList.get(i));
				}
			}

			return realWordsList;

		}

		return null;
	}

}
