package com.customweb.jtwig.lib.attribute.model;

import org.jtwig.Environment;
import org.jtwig.expressions.api.CompilableExpression;

public class NamedAttribute extends ValueAttribute {
	public NamedAttribute(CompilableExpression key, CompilableExpression value, Environment environment) {
		super(key, value, environment);
	}

	public NamedAttribute(String key, CompilableExpression value, Environment environment) {
		super(key, value, environment);
	}
	
	public NamedAttribute(String key, String value) {
		super(key, value);
	}
}