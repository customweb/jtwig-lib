package com.customweb.jtwig.lib.attribute.model.definition;

import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.Attribute;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;
import com.customweb.jtwig.lib.attribute.model.VariableAttribute;
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
	public <T extends AbstractAttributeTag<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		return new VariableAttribute(key, value);
	}

}
