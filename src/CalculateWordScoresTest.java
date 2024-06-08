import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
public class CalculateWordScoresTest {
    @Test
    public void testNullSentenceSet() {
        Assert.assertEquals(Analyzer.calculateWordScores(null), new HashMap<>());
    }

    @Test
    public void testEmptySentenceSet() {
        Set<Sentence> words = new HashSet<>();
        Assert.assertEquals(Analyzer.calculateWordScores(words), new HashMap<>());
    }  

    @Test
    public void testEmptyWordInASentence() {
        Set<Sentence> words = new HashSet<>();
        words.add(new Sentence(1, "dog ate cake ."));
        words.add(new Sentence(1, "dog  ate cake ."));
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("dog", 1.0);
        expected.put("ate", 1.0);
        expected.put("cake", 1.0);
        Assert.assertEquals(Analyzer.calculateWordScores(words), expected);
    }  

    @Test
    public void testIllegalStartCharOfWordInASentence() {
        Set<Sentence> words = new HashSet<>();
        words.add(new Sentence(1, "dog ate cake ."));
        words.add(new Sentence(1, "dog ?ate cake ."));
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("dog", 1.0);
        expected.put("ate", 1.0);
        expected.put("cake", 1.0);
        Assert.assertEquals(Analyzer.calculateWordScores(words), expected);
    }  

    @Test
    public void testSentencesWithDifferingScores() {
        Set<Sentence> words = new HashSet<>();
        words.add(new Sentence(1, "dog ate cake ."));
        words.add(new Sentence(2, "dog ate cake ."));
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("dog", 1.5);
        expected.put("ate", 1.5);
        expected.put("cake", 1.5);
        Assert.assertEquals(Analyzer.calculateWordScores(words), expected);
    }  

    @Test
    public void testSentencesWithDifferingText() {
        Set<Sentence> words = new HashSet<>();
        words.add(new Sentence(1, "dog ate cake ."));
        words.add(new Sentence(1, "cat eat pie ."));
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("dog", 1.0);
        expected.put("ate", 1.0);
        expected.put("cake", 1.0);
        expected.put("cat", 1.0);
        expected.put("eat", 1.0);
        expected.put("pie", 1.0);
        Assert.assertEquals(Analyzer.calculateWordScores(words), expected);
    }  
}