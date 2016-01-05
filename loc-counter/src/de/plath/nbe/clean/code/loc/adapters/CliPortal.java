package de.plath.nbe.clean.code.loc.adapters;

import java.util.function.Consumer;

/**
 * Adapter for the command line interface
 * 
 * @author NBeuck
 *
 */
public class CliPortal {

	/**
	 * reads the path argument from the user input
	 * 
	 * @param args
	 * @param consumer
	 */
	public static void getPathFromArguments(String[] args, Consumer<String> consumer) {
		if(args == null || args.length == 0) {
			System.out.println("Please specify a path");
			return;
		}
		consumer.accept(args[0]);
	}
	
	/**
	 * prints the count result to the console 
	 * 
	 * @param count
	 */
	public static void printLineCountToConsole(int[] count) {
		System.out.println(String .format("Found %d source files with %d code lines.", count[0], count[1]));
	}

	/**
	 * prints an exception to the console
	 * 
	 * @param e
	 */
	public static void logException(Exception e) {
		System.err.println(e);
	}
	
}
