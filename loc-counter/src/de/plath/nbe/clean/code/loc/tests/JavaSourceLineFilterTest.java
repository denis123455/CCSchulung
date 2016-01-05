package de.plath.nbe.clean.code.loc.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.plath.nbe.clean.code.loc.domain.JavaSourceLineFilter;
import de.plath.nbe.clean.code.loc.tests.util.ArgumentCaptorConsumer;

public class JavaSourceLineFilterTest {
	
	private ArgumentCaptorConsumer<List<String>> argumentCaptorConsumer;
	
	@Before 
	public void setup() {
		argumentCaptorConsumer = new ArgumentCaptorConsumer<>();
	}
	
	@Test
	public void testNullArgument() {
		JavaSourceLineFilter.filterLines(null, argumentCaptorConsumer);
		Assert.assertTrue(argumentCaptorConsumer.wasCalled());
		Assert.assertTrue(argumentCaptorConsumer.getLastArgument() == null);
	}
	
	@Test
	public void testEmptyLine() {
		JavaSourceLineFilter.filterLines(Arrays.asList("\t "), argumentCaptorConsumer);
		Assert.assertTrue(argumentCaptorConsumer.wasCalled());
		Assert.assertTrue(argumentCaptorConsumer.getLastArgument().isEmpty());
	}
	
	@Test
	public void testCodeLines() {
		List<String> lines = Arrays.asList(
				"public void testCodeLines() {",
				"	LineFilter.filterLines(lines, argumentCaptorConsumer);",
				"	Assert.assertTrue(argumentCaptorConsumer.wasCalled());",
				"}");
		
		JavaSourceLineFilter.filterLines(lines, argumentCaptorConsumer);
		
		Assert.assertTrue(argumentCaptorConsumer.wasCalled());
		Assert.assertEquals(4, argumentCaptorConsumer.getLastArgument().size());
	}
	
	@Test
	public void testSingleLineComments() {
		List<String> lines = Arrays.asList(
				"public void testCodeLines() {",
				"	// LineFilter.filterLines(lines, argumentCaptorConsumer);",
				"	Assert.assertTrue(true); // Assert.assertTrue(argumentCaptorConsumer.wasCalled());",
				"}");
		
		JavaSourceLineFilter.filterLines(lines, argumentCaptorConsumer);
		
		System.out.println(argumentCaptorConsumer.getLastArgument());
		Assert.assertTrue(argumentCaptorConsumer.wasCalled());
		Assert.assertEquals(3, argumentCaptorConsumer.getLastArgument().size());
	}
	
	@Test
	public void testMultiLineComments() {
		List<String> lines = Arrays.asList(
				" /* ignore ", // +0
				" this */",    // +0
				" /* and ",    // +0
				" this ",      // +0
				" too */",     // +0
				" but not /* all of */ this line",     // +1 
				" this might be tricky /*",       // +1
				" since this needs to be",        // +0
				" ignored, */ but not this",      // +1
				"    this    /* is */ /* java */ ", //+ 1
				" /* this */    is    /* java */ ", //+ 1
				" /* this */ /* is */    java    ", //+ 1
				" /* and */ \t /* finally */ /* some */ /* overkill */" //+ 0
				);
		
		JavaSourceLineFilter.filterLines(lines, argumentCaptorConsumer);
		
		// System.out.println(argumentCaptorConsumer.getLastArgument());
		Assert.assertTrue(argumentCaptorConsumer.wasCalled());
		Assert.assertEquals(6, argumentCaptorConsumer.getLastArgument().size());
	}

}
