package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class VariableAttributeDefinition extends NamedAttributeDefinition {

	public VariableAttributeDefinition(String key, boolean mandatory) {
		super(key, mandatory);
	}

	@Override
	public void validate(AttributeCollection attributes) {
		if (this.isMandatory()) {
			if (!attributes.hasAttribute(this.getKey(), VariableAttribute.class)) {
				throw new RuntimeException("The attribute '" + this.getKey() + "' is mandatory.");
			}
		}
	}

	@Override
	public <T extends AttributeModel<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		return new VariableAttribute(key, value);
	}

}
