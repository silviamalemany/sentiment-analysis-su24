
/** 
 * @author Chris Murphy
 *
 * This class represents a single sentence from the input file.
 * 
 */


public class Sentence implements Comparable<Sentence> {
	
	/**
	 * The sentiment score for the sentence. Should be in the range [-2, 2]
	 */
	private int score;
	
	/**
	 * The text contained in the sentence. 
	 */
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	public int hashCode() {
		return (score + text).hashCode();
	}
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Sentence)) return false;
		Sentence other = (Sentence) object;
		boolean streq = (text == null && other.getText() == null) || text.equals(other.getText());
		return (score == other.getScore()) && streq;
	}
	public int compareTo(Sentence o) {
		if (o == null || !(o instanceof Sentence)) {
			return -1;
		}
		boolean streq = (text == null && o.getText() == null) || text.equals(o.getText());
		if (streq) {
			return Integer.compare(score, o.getScore());
		} else {
			if (text != null) {
				return text.compareTo(o.getText());
			}
			return o.getText().compareTo(text);
		}
	}
}
