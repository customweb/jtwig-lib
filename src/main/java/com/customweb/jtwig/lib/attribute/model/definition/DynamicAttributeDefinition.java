package com.customweb.jtwig.lib.attribute.model.definition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jtwig.Environment;
import org.jtwig.expressions.api.CompilableExpression;
import org.parboiled.Rule;

import com.customweb.jtwig.lib.attribute.AttributeAddon;
import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.Attribute;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;
import com.customweb.jtwig.lib.attribute.model.DynamicAttribute;

public class DynamicAttributeDefinition extends AttributeDefinition {
	
	private Set<String> disallowedKeys = new HashSet<String>();

	public DynamicAttributeDefinition() {
		super(false);
	}
	
	public DynamicAttributeDefinition(Collection<String> disallowedKeys) {
		this();
		this.addDisallowedKeys(disallowedKeys);
	}
	
	public Set<String> getDisallowedKeys() {
		return disallowedKeys;
	}

	public DynamicAttributeDefinition addDisallowedKeys(Collection<String> disallowedKeys) {
		this.disallowedKeys.addAll(disallowedKeys);
		return this;
	}
	
	public DynamicAttributeDefinition addDisallowedKeys(String... disallowedKeys) {
		for (String disallowedKey : disallowedKeys) {
			this.addDisallowedKey(disallowedKey);
		}
		return this;
	}
	
	public DynamicAttributeDefinition addDisallowedKey(String disallowedKey) {
		this.disallowedKeys.add(disallowedKey);
		return this;
	}
	
	public DynamicAttributeDefinition resetDisallowedKeys() {
		this.disallowedKeys.clear();
		return this;
	}
	
	public DynamicAttributeDefinition removeDisallowedKey(String disallowedKey) {
		this.disallowedKeys.remove(disallowedKey);
		return this;
	}

	@Override
	public void validate(AttributeCollection attributes) {
		for (String disallowedKey : this.disallowedKeys) {
			if (attributes.hasAttribute(disallowedKey)) {
				throw new RuntimeException("The attribute '" + disallowedKey + "' is not allowed.");
			}
		}
	}

	@Override
	public <T extends AbstractAttributeTag<T>> Rule getKeyRule(AttributeAddon<T> parser) {
		return parser.identifier();
	}

	@Override
	public <T extends AbstractAttributeTag<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value, Environment environment) {
		return new DynamicAttribute(key, value, environment);
	}

}
