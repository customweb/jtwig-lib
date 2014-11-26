package com.customweb.jtwig.lib.attribute.model.definition;

import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.Attribute;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;
import com.customweb.jtwig.lib.attribute.model.EmptyAttribute;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

public class EmptyAttributeDefinition extends NamedAttributeDefinition {

	public EmptyAttributeDefinition(String key) {
		super(key, false);
	}

	@Override
	public void validate(AttributeCollection attributes) {
		if (this.isMandatory()) {
			if (!attributes.hasAttribute(this.getKey(), EmptyAttribute.class)) {
				throw new RuntimeException("The attribute '" + this.getKey() + "' is mandatory.");
			}
		}
	}

	@Override
	public <T extends AbstractAttributeTag<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		if (value != null) {
			throw new RuntimeException("The empty attribute '" + key + "' cannot have a value.");
		}
		return new EmptyAttribute(key);
	}

}
