package edu.institution.finalproj;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import org.junit.Assert;

public class FinalProjectTests {

	@Test
	public void anagramTest() {
		AnagramDataReaderImpl dr = new AnagramDataReaderImpl();
		AnagramEvaluatorImpl evaluator = new AnagramEvaluatorImpl();
		Set<String> list = dr.readData();

		// Test to ensure that list of real words is present on system. If this fails
		// then the anagram_data.txt file is missing
		Assert.assertTrue(list.contains("HYMNS"));

		String noFilterWord = "EDNA";
		List<String> nfList = evaluator.evaluate(noFilterWord, "nf");

		// Asserts that a no-filter search results in an accurate list of 24 options
		// under search "EDNA"
		Assert.assertTrue(nfList.size() == 24);
		
		
		AnnagramCLI.main(new String[] { "-nf", "-a", "EDNA" }); // Test run on the CLI

		AnnagramCLI.main(new String[] { "-nf", "EDNA" }); // Test to make sure -a is required
		

	}

	@Test
	public void wordsTest() {
		AnagramEvaluatorImpl evaluator = new AnagramEvaluatorImpl();

		String filterWords = "DOG";
		String filterWordCases = "dog";
		List<String> wordsList = evaluator.evaluate(filterWords, "word");
		List<String> casesList = evaluator.evaluate(filterWordCases, "word");

		// Asserts the evaluator is case insensitive
		Assert.assertEquals(wordsList, casesList);
		
		
		

		// Asserts that running a filtered search on a word results in the correct sized
		// list, indicating a successful search
		AnnagramCLI.main(new String[] { "-words", "-a", "DOG" });
		Assert.assertTrue(wordsList.size() == 2);

		AnnagramCLI.main(new String[] { "-help" });

	}

}
