package de.plath.nbe.clean.code.loc.domain;

import java.util.function.Consumer;

/**
 * hands over filenames that java the java extension and rejects all others
 * 
 * @author NBeuck
 */
public class JavaSourceFileFilter {

	/**
	 * lets only java file names pass
	 * 
	 * passes on null arguments
	 * 
	 * @param filename
	 * @param consumer
	 */
	public static void filterForSourceFiles(String filename, Consumer<String> consumer) {
		if(filename == null) {
			consumer.accept(null);
			return;
		}
				
		if(isJavaFileName(filename)) {
			consumer.accept(filename);
		}
	}

	private static boolean isJavaFileName(String filename) {
		return filename.endsWith(".java");
	}
}
