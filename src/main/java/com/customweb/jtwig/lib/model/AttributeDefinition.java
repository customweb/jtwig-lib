package com.customweb.jtwig.lib.model;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.AttributeAddon;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

public abstract class AttributeDefinition {
	private final boolean mandatory;

	public AttributeDefinition(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isMandatory() {
		return mandatory;
	}
	
	abstract public <T extends AttributeModel<T>> void validate(AttributeCollection<T> attributes);

	abstract public <T extends AttributeModel<T>> Rule getKeyRule(
			AttributeAddon<T> parser);
	
	abstract public <T extends AttributeModel<T>> Attribute getAttributeInstance(CompilableExpression key, CompilableExpression value, AttributeAddon<T> parser);

}
