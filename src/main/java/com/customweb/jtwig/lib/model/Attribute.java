package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;

abstract public class Attribute {
	private String key;
	private String value;

	@SuppressWarnings("unchecked")
	public Attribute(CompilableExpression key, CompilableExpression value) {
		this((String) ((Constant<String>) key).as(String.class).toLowerCase(), value);
	}

	@SuppressWarnings("unchecked")
	public Attribute(String key, CompilableExpression value) {
		this.key = key.toLowerCase();
		this.value = (String) ((Constant<String>) value).as(String.class);
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return getKey() + "=\"" + getValue() + "\"";
	}
}