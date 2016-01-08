package de.ccd.spellchecker;

import java.util.stream.Stream;

import de.ccd.spellchecker.adapter.CLIPortal;
import de.ccd.spellchecker.adapter.CheckFilterProvider;
import de.ccd.spellchecker.adapter.FSProvider;
import de.ccd.spellchecker.domain.Tokenizer;
import pods.WordTriple;
/**
 * Very simple spellchecker that applies wordwise checking to all words in a textfile.   
 * @author DStier
 *
 */
public class SpellChecker {
	/**
	 * Assumes exactly one argument, namely the path to the textfile to check.
	 * @param args 
	 */
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("Missmatching argument count. Found " + args.length + " expected " + 1);
			return;
		}
		Tokenizer<String, Stream<WordTriple>> tokenizer = new Tokenizer<String, Stream<WordTriple>>();
		
		Stream.of(args[0]).flatMap(FSProvider.FILELOADER)
		.flatMap(tokenizer).filter(CheckFilterProvider.SPELLINGFILTER)
		.forEach(CLIPortal.PRINTER);
	}
}
