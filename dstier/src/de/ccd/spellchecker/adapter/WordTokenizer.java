package de.ccd.spellchecker.adapter;

import java.util.function.Function;
import java.util.stream.Stream;
/**
 * A Tokenizer creating single words out of a line of words and puts them in a sequential stream.
 * 
 * @author DStier
 *
 */
public class WordTokenizer implements Function< String, Stream<String>> {
	/**
	 * Applies the transformation to the given input line.
	 * @param inputRow : The line of words to transform.
	 */
	@Override
	public Stream<String> apply(String inputRow) {
		 String[] split = inputRow.split("[\\s']");
		 return Stream.of(split).map(in -> in.trim()).filter(word -> !word.isEmpty());
	}

}
