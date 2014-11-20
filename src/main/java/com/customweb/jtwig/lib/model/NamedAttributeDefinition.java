package com.customweb.jtwig.lib.model;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.AttributeAddon;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class NamedAttributeDefinition extends AttributeDefinition {

	private final String key;

	public NamedAttributeDefinition(String key, boolean mandatory) {
		super(mandatory);
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	@Override
	public <T extends AttributeModel<T>> void validate(
			AttributeCollection<T> attributes) {
		if (this.isMandatory()) {
			if (!attributes.hasAttribute(this.getKey(), NamedAttribute.class)) {
				throw new RuntimeException("The attribute '" + this.getKey()
						+ "' is mandatory.");
			} else if (attributes.getAttribute(this.getKey(),
					NamedAttribute.class).getValue() == null
					|| attributes
							.getAttribute(this.getKey(), NamedAttribute.class)
							.getValue().isEmpty()) {
				throw new RuntimeException("The value of the attribute '"
						+ attributes.getAttribute(this.getKey()).getValue()
						+ "' has not been set.");
			}
		}
	}

	@Override
	public <T extends AttributeModel<T>> Rule getKeyRule(
			AttributeAddon<T> parser) {
		return parser.IgnoreCase(this.getKey());
	}

	@Override
	public <T extends AttributeModel<T>> Attribute getAttributeInstance(
			CompilableExpression key, CompilableExpression value,
			AttributeAddon<T> parser) {
		return new NamedAttribute(key, value);
	}

}
