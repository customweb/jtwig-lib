package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;

abstract public class ValueAttribute extends Attribute {
	private String value;

	@SuppressWarnings("unchecked")
	public ValueAttribute(CompilableExpression key, CompilableExpression value) {
		this((String) ((Constant<String>) key).as(String.class).toLowerCase(), value);
	}

	@SuppressWarnings("unchecked")
	public ValueAttribute(String key, CompilableExpression value) {
		this(key, (String) ((Constant<String>) value).as(String.class));
	}
	
	public ValueAttribute(String key, String value) {
		super(key);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return getKey() + "=\"" + getValue() + "\"";
	}
}
