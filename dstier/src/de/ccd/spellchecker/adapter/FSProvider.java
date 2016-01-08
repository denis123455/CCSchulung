package de.ccd.spellchecker.adapter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;
/**
 * A FileSystemProvider allowing access to a file loading Function.
 * @author DStier
 *
 */
public class FSProvider {
	/**
	 * A simple fileloader which provides a stream of lines from a textfile.
	 * 
	 * @author DStier
	 *
	 */
	private static class FileLoader implements Function<String, Stream<String>> {
		/**
		 * Loads the textfile with the given pathname.
		 * @param The path of the file to load
		 * @return A Stringstream of lines in the loaded file.
		 */
		@Override
		public Stream<String> apply(String pathName) {
			try {
				return Files.lines(Paths.get(pathName),StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	public static final Function<String, Stream<String>> FILELOADER = new FileLoader();
}
