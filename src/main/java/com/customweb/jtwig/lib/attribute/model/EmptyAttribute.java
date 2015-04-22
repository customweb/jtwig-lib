package com.customweb.jtwig.lib.attribute.model;

import org.jtwig.expressions.api.CompilableExpression;

public class EmptyAttribute extends Attribute {
	public EmptyAttribute(CompilableExpression key) {
		super(key);
	}
	
	public EmptyAttribute(String key) {
		super(key);
	}
}
