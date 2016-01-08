package pods;
/**
 * A immutable triplet with 3 Members consisting of (Word,position in line, linenumber).
 * @author DStier
 *
 */
public class WordTriple {
	private int column;
	private int row;
	private String word;
	/**
	 * Creates a new Triplet with the given data
	 * @param column : position of the word in the given line
	 * @param row : the line number
	 * @param word : the actual word.
	 */
	public WordTriple(int column, int row,String word) {
		this.column = column;
		this.row = row;
		this.word = word;
	}
	/**
	 * 
	 * @return The position in the line
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * 
	 * @return The linenumber
	 */
	public int getRow() {
		return row;
	}
	/**
	 * 
	 * @return The word.
	 */
	public String getWord() {
		return word;
	}
}
