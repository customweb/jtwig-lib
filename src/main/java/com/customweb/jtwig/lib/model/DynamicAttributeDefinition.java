package com.customweb.jtwig.lib.model;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.AttributeAddon;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class DynamicAttributeDefinition extends AttributeDefinition {

	public DynamicAttributeDefinition() {
		super(false);
	}

	@Override
	public <T extends AttributeModel<T>> void validate(AttributeCollection<T> attributes) {
	}

	@Override
	public <T extends AttributeModel<T>> Rule getKeyRule(AttributeAddon<T> parser) {
		return parser.basicParser().identifier();
	}

	@Override
	public <T extends AttributeModel<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		return new DynamicAttribute(key, value);
	}

}
