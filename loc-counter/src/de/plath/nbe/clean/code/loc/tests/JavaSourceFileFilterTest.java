package de.plath.nbe.clean.code.loc.tests;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import de.plath.nbe.clean.code.loc.domain.JavaSourceFileFilter;
import de.plath.nbe.clean.code.loc.tests.util.AssertCalledConsumer;
import de.plath.nbe.clean.code.loc.tests.util.AssertNotCalledConsumer;

public class JavaSourceFileFilterTest {

	private AssertCalledConsumer<String> acceptedConsumer = new AssertCalledConsumer<String>();	
	private Consumer<String> rejectedConsumer = new AssertNotCalledConsumer<String>();
	
	@Before 
	public void setup() {
		acceptedConsumer.reset();
	}
	
	@Test
	public void testfilterFilename() {
		String java = "foo.java";
		String txt = "bar.txt";
		
		JavaSourceFileFilter.filterForSourceFiles(java, acceptedConsumer);
		acceptedConsumer.assertConsumerCalled();
		
		JavaSourceFileFilter.filterForSourceFiles(txt, rejectedConsumer);
	}
}
