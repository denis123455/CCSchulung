package de.ccd.spellchecker.adapter;

import java.util.function.Predicate;

import pods.WordTriple;
/**
 * A Filterprovider providing a Spellfilter that works with {@linkplain WordTriple} pods.
 * @author DStier
 *
 */
public class CheckFilterProvider {
	/**
	 * A Filter providing rudimentary spellchecking.
	 * @author DStier
	 *
	 */
	private static class SpellingFilter implements Predicate<WordTriple> {
		/**
		 * Tests weather a given {@linkplain WordTriple}s word part is spelled correctly according to the loaded dictionary.
		 * @param toTest The data to test against the Dictionary.
		 * @return True iff a word is spelled incorrectly, false otherwise.
		 */
		@Override
		public boolean test(WordTriple toTest) {
			String word = toTest.getWord();
			return !DictionaryProvider.Dictionary.isSpelledCorrectly(word);
		}	
	}
	public static final Predicate<WordTriple> SPELLINGFILTER = new SpellingFilter();

}
