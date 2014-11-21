package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class DynamicAttribute extends ValueAttribute {
	public DynamicAttribute(CompilableExpression key, CompilableExpression value) {
		super(key, value);
	}
}