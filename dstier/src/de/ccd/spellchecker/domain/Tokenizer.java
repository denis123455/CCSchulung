package de.ccd.spellchecker.domain;


import java.util.function.Function;
import java.util.stream.Stream;

import de.ccd.spellchecker.adapter.WordTokenizer;
import pods.WordTriple;

/**
 * A Tokenizer which creates {@linkplain WordTriple}s from a Stringstream of lines.
 * @author DStier
 *
 * @param <T>
 * @param <R>
 */
public class Tokenizer<T,R> implements Function< String, Stream<WordTriple>>{
	/**
	 * Applies the transofrmation.
	 * @param inputRow The line to split and package into a stream of {@linkplain WordTriple}s
	 */
	@Override
	public Stream<WordTriple> apply(String inputRow) {
		lineCount++;
		 return new WordTokenizer().apply(inputRow).map(word -> create(word));
	}
	
	private int lineCount;
	private int columnCount;
	private WordTriple create(String input){
		WordTriple triple = new WordTriple(columnCount, lineCount, input);
		//+1 since we assume whitespaces between words.
		columnCount += input.length() +1;
		return triple;
	}
}
