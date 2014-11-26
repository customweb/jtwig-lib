package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class NamedAttribute extends ValueAttribute {
	public NamedAttribute(CompilableExpression key, CompilableExpression value) {
		super(key, value);
	}

	public NamedAttribute(String key, CompilableExpression value) {
		super(key, value);
	}
	
	public NamedAttribute(String key, String value) {
		super(key, value);
	}
}