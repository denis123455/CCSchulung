package de.plath.nbe.clean.code.loc.tests.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArgumentCaptorConsumer<T> implements Consumer<T>{

	private List<T> arguments = new ArrayList<T>();
	
	@Override
	public void accept(T t) {
		arguments.add(t);
	}
	
	public int getInvocations() {
		return arguments.size();
	}
	
	public boolean wasCalled() {
		return !arguments.isEmpty();
	}
	
	public T getLastArgument() {
		return arguments.get(arguments.size()-1);
	}
	
	public List<T> getAllArguments() {
		return new ArrayList<>(arguments);
	}
	
	public void reset() {
		arguments.clear();
	}
	
}
