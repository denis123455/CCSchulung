package de.plath.nbe.clean.code.loc.tests.util;

import java.util.function.Consumer;

import org.junit.Assert;

public class AssertNotCalledConsumer<T> implements Consumer<T> {
	@Override
	public void accept(T t) {
		Assert.fail("Unexpected call with argument " + t);
	}
}
