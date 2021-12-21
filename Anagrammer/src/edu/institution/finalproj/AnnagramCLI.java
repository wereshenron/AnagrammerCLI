package edu.institution.finalproj;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AnnagramCLI {

	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = createOptions();
		AnagramEvaluatorImpl anagram = new AnagramEvaluatorImpl();

		try {
			String argString = "";
			for (int i = 0; i < args.length; i++) {
				argString = argString + args[i] + " ";
			}
			CommandLine line = parser.parse(options, args);
			System.out.println("Annagrammer " + argString);

			// This section will be designated for handling a/no-filter input
			if ((line.hasOption("a") || line.hasOption("anagram"))
					&& (line.hasOption("nf") || line.hasOption("no-filter"))) {
				String word = line.getOptionValue("a");
				List<String> anagrams = anagram.evaluate(word, "nf");
				for (int i = 0; i < anagrams.size(); i++) {
					System.out.println(anagrams.get(i));
				}
				System.out.println("-- " + anagrams.size() + (" value(s) found.\n"));
			} else if (line.hasOption("nf") && !(line.hasOption("a") || line.hasOption("anagram"))) {
				throw new ParseException("Missing required option: -a");
			}
			///////////////////////////////////////////////////////////

			// This section will be designated for handling a/word-filter input

			if ((line.hasOption("a") || line.hasOption("anagram"))
					&& (line.hasOption("words") || line.hasOption("filter-words"))) {
				String word = line.getOptionValue("a");
				List<String> realWords = anagram.evaluate(word, "word");
				for (int i = 0; i < realWords.size(); i++) {
					System.out.println(realWords.get(i));
				}
				System.out.println("-- " + realWords.size() + (" value(s) found.\n"));
			} else if (line.hasOption("words")
					|| line.hasOption("filter-words") && !(line.hasOption("a") || line.hasOption("anagram"))) {
				throw new ParseException("Missing required option: -a");
			}
			///////////////////////////////////////////////////////////

			// This section is set aside to handle help input

			else if (line.hasOption("h") || line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("annagrammer", options);
			}
			///////////////////////////////////////////////////////////

		} catch (ParseException exp) {

			System.err.println("Parsing failed.  Reason: " + exp.getMessage() + "\n");
		}

	}

	public static Options createOptions() {
		Option anagram = new Option("a", "anagram", true, "supplies anagram to evaluate");
		anagram.hasArg();
		anagram.setArgName("word");
		Option help = new Option("h", "help", false, "displays this help text");
		Option noFilter = new Option("nf", "no-filter", false, "displays all possible anagrams with no filter");
		Option words = new Option("words", "filter-words", false, "displays values that are only known words");

		Options options = new Options();
		options.addOption(anagram);
		options.addOption(help);
		options.addOption(noFilter);
		options.addOption(words);

		return options;
	}

}
