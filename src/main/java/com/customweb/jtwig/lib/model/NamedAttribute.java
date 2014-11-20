package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class NamedAttribute extends Attribute {
	public NamedAttribute(CompilableExpression key, CompilableExpression value) {
		super(key, value);
	}

	public NamedAttribute(String key, CompilableExpression value) {
		super(key, value);
	}
}