package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;

abstract public class Attribute {
	private String key;

	@SuppressWarnings("unchecked")
	public Attribute(CompilableExpression key) {
		this((String) ((Constant<String>) key).as(String.class).toLowerCase());
	}
	
	public Attribute(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String toString() {
		return getKey();
	}
}