package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.parser.config.ParserConfiguration;

public class NamedAttribute extends ValueAttribute {
	public NamedAttribute(CompilableExpression key, CompilableExpression value, ParserConfiguration configuration) {
		super(key, value, configuration);
	}

	public NamedAttribute(String key, CompilableExpression value, ParserConfiguration configuration) {
		super(key, value, configuration);
	}
	
	public NamedAttribute(String key, String value) {
		super(key, value);
	}
}