package com.customweb.jtwig.lib.attribute.model.definition;

import org.jtwig.Environment;
import org.jtwig.expressions.api.CompilableExpression;
import org.parboiled.Rule;

import com.customweb.jtwig.lib.attribute.AttributeAddon;
import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.Attribute;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;

public abstract class AttributeDefinition {
	private final boolean mandatory;

	public AttributeDefinition(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	abstract public void validate(AttributeCollection attributes);

	abstract public <T extends AbstractAttributeTag<T>> Rule getKeyRule(AttributeAddon<T> parser);

	abstract public <T extends AbstractAttributeTag<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value, Environment environment);

}
