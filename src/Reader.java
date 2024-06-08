

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws FileNotFoundException 
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		/*
		 * Implement this method in Part 1
		 */
		if (filename == null) throw new IllegalArgumentException();
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
			Set<Sentence> result = new HashSet<>();
			String line;
			String[] splits;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				splits = line.split(" ", 2);
				if (splits.length != 2) {
					continue;
				}
				int score;
				try {
					score = Integer.parseInt(splits[0]);
				} catch (NumberFormatException e) {
					continue;
				}
				if (score >= -2 && score <= 2 && splits[1].length() != 0) {

					result.add(new Sentence(score, splits[1]));
				}
			}
			scanner.close();
			return result;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
}
