package de.plath.nbe.clean.code.loc.domain;

import java.util.List;
import java.util.function.Consumer;

/**
 * accumulates partial results for individual files
 * every list if strings given is assumed to represent exactly one file
 * 
 * end of stream is indicated by a null,
 * in that case the accumulated result is passed to a consumer
 * 
 * @author NBeuck
 *
 */
public class LineCounter {

	private int fileAccumulator = 0;
	private int lineAccumulator = 0;
	
	/**
	 * @param lines the lines to add, null indicates EOS
	 * @param consumer called only if the argument is null 
	 */
	public void countLines(List<String> lines, Consumer<int[]> consumer) {
		if(lines == null) {
			consumer.accept(new int[] {fileAccumulator, lineAccumulator});
			fileAccumulator = 0;
			lineAccumulator = 0;
		} else {
			fileAccumulator += 1;
			lineAccumulator += lines.size();
		}
	}
}
