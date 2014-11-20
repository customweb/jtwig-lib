package com.customweb.jtwig.lib.model;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.AttributeAddon;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class VariableAttributeDefinition extends AttributeDefinition {

	private final String key;

	public VariableAttributeDefinition(String key, boolean mandatory) {
		super(mandatory);
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	@Override
	public <T extends AttributeModel<T>> void validate(AttributeCollection<T> attributes) {
		if (this.isMandatory()) {
			if (!attributes.hasAttribute(this.getKey(), VariableAttribute.class)) {
				throw new RuntimeException("The attribute '" + this.getKey() + "' is mandatory.");
			}
		}
	}

	@Override
	public <T extends AttributeModel<T>> Rule getKeyRule(AttributeAddon<T> parser) {
		return parser.IgnoreCase(this.getKey());
	}

	@Override
	public <T extends AttributeModel<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		return new VariableAttribute(key, value);
	}

}
