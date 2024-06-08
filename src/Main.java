import java.io.*;
import java.util.*;
/**
 * 
 * Main class for the sentiment analysis program.
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("no input file");
		} else {
			try {
				Scanner scanner = new Scanner(System.in);
				Map<String, Double> wordScores = Analyzer.calculateWordScores(Reader.readFile(args[0]));
				while (true) {
					System.out.println("enter a sentence:");
					String sentence = scanner.nextLine().trim();
					System.out.println("sentence: " + sentence);
					if (sentence.equals("quit")) {
						break;
					}
					System.out.println(String.format("sentence score is %f", Analyzer.calculateSentenceScore(wordScores, sentence)));
				}
				scanner.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.out.println("bad input file");
			}
		}
	}
}
