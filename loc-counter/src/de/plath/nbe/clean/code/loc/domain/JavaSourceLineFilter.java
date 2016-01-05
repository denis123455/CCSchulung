package de.plath.nbe.clean.code.loc.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * hands over all lines that contain code
 * rejects comments and empty lines
 * 
 * assumes that there are no nested comments of mixed types,
 * especially multi-line comment delimiters inside single line comments are not handled correctly (i.e. are not ignored)
 * 
 * @author NBeuck
 *
 */
public class JavaSourceLineFilter {
	
	public static final String START_COMMENT = "/*";
	public static final String STOP_COMMENT = "*/";
	public static final String LINE_COMMENT = "//";
	
	/**
	 * removes all comments and empty lines as well as leading and trailing whitespace
	 * 
	 * @param lines the lines to filter
	 * @param consumer the consumer 
	 */
	public static void filterLines(List<String> lines, Consumer<List<String>> consumer) {
		if(lines == null) {
			consumer.accept(null);
			return;
		}
		
		MultiLineCommentFilter multiLineCommentFilter = new MultiLineCommentFilter();
		
		List<String> filteredLines = lines.stream().
			map(multiLineCommentFilter::filter).
			map(JavaSourceLineFilter::filterSingleLineComment).
			map(String::trim).
			filter(line -> !line.isEmpty()).
			collect(Collectors.toList());
		
		consumer.accept(filteredLines);
	}
	
	private static String filterSingleLineComment(String line) {
		return line.split(Pattern.quote(LINE_COMMENT), 2)[0]; 
	}
	
	private static class MultiLineCommentFilter {  
		private boolean insideComment = false;
		
		public String filter(String line) {
			if(insideComment) {
				if(line.contains(STOP_COMMENT)) {
					insideComment = false;
					return filter(line.split(Pattern.quote(STOP_COMMENT), 2)[1]);
				} else {
					return "";
				}
			} else {
				if(line.contains(START_COMMENT)) {
					insideComment = true;
					String[] split = line.split(Pattern.quote(START_COMMENT), 2);
					return split[0] + filter(split[1]);
				} else {
					return line;
				}
			}
		}
	}
	
}
