package de.ccd.spellchecker.adapter;
import java.util.function.Consumer;

import pods.WordTriple;
/**
 * Portal for LCI interactions.
 * @author DStier
 *
 */
public class CLIPortal {
	/**
	 * A Consumer formatting and printing {@linkplain WordTriple}s
	 * 
	 * @author DStier
	 *
	 */
	private static class Printer implements Consumer<WordTriple> {
		/**
		 * @param wt The pod to format and print.
		 */
		@Override
		public void accept(WordTriple wt) {
			System.out.println("Spelling mismatch at Word: " + wt.getWord() + " at position: " + wt.getColumn() + " in Line number: " + wt.getRow());
			return;
		}
	}

	public static final Consumer<WordTriple> PRINTER = new Printer();
}
