package de.plath.nbe.clean.code.loc;

import java.util.function.Consumer;

import de.plath.nbe.clean.code.loc.adapters.CliPortal;
import de.plath.nbe.clean.code.loc.adapters.FileSystemProvider;
import de.plath.nbe.clean.code.loc.domain.JavaSourceFileFilter;
import de.plath.nbe.clean.code.loc.domain.JavaSourceLineFilter;
import de.plath.nbe.clean.code.loc.domain.LineCounter;

public class LocApp {
	
	public static void main(String[] args) {
		CliPortal.getPathFromArguments(args, 
				(path -> countLines(path, 
						(CliPortal::printLineCountToConsole), 
						(CliPortal::logException))));
	}
	
	private static void countLines(String path, Consumer<int[]> resultConsumer, Consumer<Exception> errorConsumer) {
		LineCounter lineCounter = new LineCounter();
		
		FileSystemProvider.getFilesInDirectory(path,
				filename -> JavaSourceFileFilter.filterForSourceFiles(filename, 
						sourcefilename -> FileSystemProvider.getLinesForFileName(sourcefilename, 
								lines -> JavaSourceLineFilter.filterLines(lines, 
										filteredLines -> lineCounter.countLines(filteredLines, 
												resultConsumer)),
								errorConsumer)), 
				errorConsumer);
	}
	
	
}
