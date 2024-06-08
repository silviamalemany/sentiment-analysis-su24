

import java.util.*;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		/*
		 * Implement this method in Part 2
		 */
		if (sentences == null || sentences.isEmpty()) return new HashMap<>();
		Map<String, Double> result = new HashMap<>();
		Map<String, Integer> counts = new HashMap<>();
		for (Sentence sentence : sentences) {
			String[] text = sentence.getText().split(" ");
			for (String word : text) {
				word = word.toLowerCase().strip();
				if (word.length() == 0 || word.matches("[^a-zA-Z].*")) {
					continue;
				} 
				result.put(word, result.getOrDefault(word, 0.0) + sentence.getScore());
				counts.put(word, counts.getOrDefault(word, 0) + 1);

			}
		}
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0.0)/entry.getValue());
		}
		return result;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		if (sentence == null || sentence.isEmpty()) return 0.0;
		double num = 0.0;
		double denom = 0.0;
		String[] text = sentence.split(" ");
		for (String word : text) {
			if (word.length() == 0 || word.matches("[^a-zA-Z].*")) {
				continue;
			}
			word = word.toLowerCase().strip();
			num += wordScores.getOrDefault(word, 0.0);
			if (wordScores.containsKey(word)) denom += 1;
		}
		if (denom == 0) return 0;
		return num/denom;
	}
}
