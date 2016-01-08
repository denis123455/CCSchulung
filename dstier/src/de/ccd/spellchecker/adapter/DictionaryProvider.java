package de.ccd.spellchecker.adapter;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Provider for the dictionary used.
 * @author DStier
 *
 */
public class DictionaryProvider {
	/**
	 * A pseudo dictionary, loading a hardcoded file of words and provides functionality to check weather a given String is contained in the dcitionary. 
	 * @author DStier
	 *
	 */
	public static class Dictionary {

		private final static String DICTPATH = "dictionary.txt";
		private static final HashSet<String> dictionary = init();
		
		private static HashSet<String> init() {
			Stream<String> dictEntries = FSProvider.FILELOADER.apply(DICTPATH).flatMap(new WordTokenizer());
			HashSet<String> tempDict = new HashSet<String>();
			tempDict.addAll(dictEntries.collect(Collectors.toList()));
			return tempDict;
		}
		/**
		 * 
		 * @param toCheck The word to check against the dictionary.
		 * @return True if the word is contained in the dictionary, false otherwise.
		 */
		public static Boolean isSpelledCorrectly(String toCheck) {
			return dictionary.contains(toCheck);
		}
	}
}
