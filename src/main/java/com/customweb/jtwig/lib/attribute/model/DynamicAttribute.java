package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.parser.config.ParserConfiguration;

public class DynamicAttribute extends ValueAttribute {
	public DynamicAttribute(CompilableExpression key, CompilableExpression value, ParserConfiguration configuration) {
		super(key, value, configuration);
	}
	
	public DynamicAttribute(String key, String value) {
		super(key, value);
	}
}