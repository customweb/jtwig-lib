package com.customweb.jtwig.lib.attribute.model;

import org.jtwig.Environment;
import org.jtwig.expressions.api.CompilableExpression;

public class DynamicAttribute extends ValueAttribute {
	public DynamicAttribute(CompilableExpression key, CompilableExpression value, Environment environment) {
		super(key, value, environment);
	}
	
	public DynamicAttribute(String key, String value) {
		super(key, value);
	}
}