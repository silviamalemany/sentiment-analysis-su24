import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CalculateSentenceScoreTest {
    @Test
    public void testNullSentence() {
        Assert.assertEquals(Analyzer.calculateSentenceScore(new HashMap<String, Double>(), null), 0.0, 0.0);
    }
    
    @Test
    public void testEmptySentence() {
        Assert.assertEquals(Analyzer.calculateSentenceScore(new HashMap<String, Double>(), ""), 0.0, 0.0);
    }

    @Test
    public void testEmptyWordInSentence() {
        HashMap<String, Double> wordScores = new HashMap<>();
        wordScores.put("dog", 1.0);
        wordScores.put("ate", 7.0);
        wordScores.put("cake", 1.0);
        // double space creates an empty string ("") as one of the words processed when split is called
        String sentence = "dog  ate cake .";
        Assert.assertEquals(Analyzer.calculateSentenceScore(wordScores, sentence), 3.0, 0.0);
    }  

    @Test
    public void testIllegalStartCharOfWordInSentence() {
        HashMap<String, Double> wordScores = new HashMap<>();
        wordScores.put("dog", 1.0);
        wordScores.put("ate", 7.0);
        wordScores.put("cake", 1.0);
        String sentence = "dog  ?ate cake .";
        Assert.assertEquals(Analyzer.calculateSentenceScore(wordScores, sentence), 1.0, 0.0);
    }  

    @Test
    public void testMissingWordsInWordScoresMap() {
        HashMap<String, Double> wordScores = new HashMap<>();
        wordScores.put("dog", 1.0);
        wordScores.put("ate", 7.0);
        String sentence = "dog ate cake .";
        Assert.assertEquals(Analyzer.calculateSentenceScore(wordScores, sentence), 4.0, 0.0);
    }  
    
    @Test
    public void testDenominatorZero() {
        HashMap<String, Double> wordScores = new HashMap<>();
        wordScores.put("dog", 1.0);
        wordScores.put("ate", 7.0);
        String sentence = "cake cake cake .";
        Assert.assertEquals(Analyzer.calculateSentenceScore(wordScores, sentence), 0.0, 0.0);
    }  
}