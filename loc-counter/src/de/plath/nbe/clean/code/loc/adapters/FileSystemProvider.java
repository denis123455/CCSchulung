package de.plath.nbe.clean.code.loc.adapters;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FileSystemProvider {
	
	/**
	 * reads the content of a file as a List of strings
	 * 
	 * @param filename the name of the file to read
	 * @param consumer read lines are a passed here
	 * @param errorConsumer exceptions are passed here
	 */
	public static void getLinesForFileName(String filename, Consumer<List<String>> consumer, Consumer<Exception> errorConsumer) {
		if(filename == null) {
			consumer.accept(null);
			return;
		}
		
		try {
			consumer.accept(Files.lines(Paths.get(filename)).collect(Collectors.toList()));
		} catch (IOException e) {
			errorConsumer.accept(e);
		}
	}
	
	/**
	 * finds all files recursively in the given path
	 * a null is passed to the consumer once the 
	 * 
	 * @param path 
	 * @param consumer paths for found files are passed here
	 * @param errorConsumer exceptions are passed here
	 */
	public static void getFilesInDirectory(String path, Consumer<String> consumer, Consumer<Exception> errorConsumer) {
		getFilesFromDirectoryImpl(path, consumer, errorConsumer);
		consumer.accept(null);
	}

	
	private static void getFilesFromDirectoryImpl(String path, Consumer<String> consumer, Consumer<Exception> errorConsumer) {
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path));
			for (Path entry : stream) {
	            String pathAsString = entry.toString();
				if (Files.isDirectory(entry)) {
					getFilesFromDirectoryImpl(pathAsString, consumer, errorConsumer);
	            } else {
	            	consumer.accept(pathAsString);
	            }
	        }
		} catch (IOException e) {
			errorConsumer.accept(e);
		}
	}
}
