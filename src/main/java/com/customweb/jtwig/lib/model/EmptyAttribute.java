package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class EmptyAttribute extends Attribute {
	public EmptyAttribute(CompilableExpression key) {
		super(key);
	}
	
	public EmptyAttribute(String key) {
		super(key);
	}
}
