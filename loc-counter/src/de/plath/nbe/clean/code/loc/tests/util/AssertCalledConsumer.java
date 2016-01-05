package de.plath.nbe.clean.code.loc.tests.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.junit.Assert;

public class AssertCalledConsumer<T> implements Consumer<T>{

	private AtomicBoolean consumerCalled = new AtomicBoolean(false);
	
	@Override
	public void accept(T t) {
		consumerCalled.set(true);
	}
	

	public void assertConsumerCalled() {
		Assert.assertTrue(consumerCalled.get());
		reset();
	}
	
	public void reset() {
		consumerCalled.set(false);
	}
	
}
