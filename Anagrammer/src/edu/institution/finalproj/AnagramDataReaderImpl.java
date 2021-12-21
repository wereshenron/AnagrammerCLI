package edu.institution.finalproj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AnagramDataReaderImpl implements AnagramDataReader {
	
	// This class requires the user to have the anagram_data.txt file submitted in their documents folder
	
	private static String PATH = System.getProperty("user.home") + File.separator + ("Documents");
	private static String FILE_NAME = ("/anagram_data.txt");

	public Set<String> readData() {
		Set<String> words = new HashSet<String>();
		
		File file = new File(PATH + FILE_NAME);
		if (file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine()) {
					words.add(sc.nextLine());
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return words;
	}

}
