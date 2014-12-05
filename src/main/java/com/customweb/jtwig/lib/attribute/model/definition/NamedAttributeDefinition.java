package com.customweb.jtwig.lib.attribute.model.definition;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.attribute.AttributeAddon;
import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.Attribute;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;
import com.customweb.jtwig.lib.attribute.model.NamedAttribute;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.parser.config.ParserConfiguration;

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
	public void validate(AttributeCollection attributes) {
		if (this.isMandatory()) {
			if (!attributes.hasAttribute(this.getKey(), NamedAttribute.class)) {
				throw new RuntimeException("The attribute '" + this.getKey() + "' is mandatory.");
			} else if (!attributes.getAttribute(this.getKey(), NamedAttribute.class).isValid()) {
				throw new RuntimeException("The value of the attribute '" + this.getKey() + "' has not been set.");
			}
		}
	}

	@Override
	public <T extends AbstractAttributeTag<T>> Rule getKeyRule(AttributeAddon<T> parser) {
		return parser.IgnoreCase(this.getKey());
	}

	@Override
	public <T extends AbstractAttributeTag<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value, ParserConfiguration configuration) {
		return new NamedAttribute(key, value, configuration);
	}

}
