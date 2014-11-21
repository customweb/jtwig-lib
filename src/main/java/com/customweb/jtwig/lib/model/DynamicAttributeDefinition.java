package com.customweb.jtwig.lib.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.AttributeAddon;
import com.lyncode.jtwig.expressions.api.CompilableExpression;

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

	public void addDisallowedKeys(Collection<String> disallowedKeys) {
		this.disallowedKeys.addAll(disallowedKeys);
	}
	
	public void addDisallowedKey(String disallowedKey) {
		this.disallowedKeys.add(disallowedKey);
	}
	
	public void resetDisallowedKeys() {
		this.disallowedKeys.clear();
	}
	
	public void removeDisallowedKey(String disallowedKey) {
		this.disallowedKeys.remove(disallowedKey);
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
	public <T extends AttributeModel<T>> Rule getKeyRule(AttributeAddon<T> parser) {
		return parser.basicParser().identifier();
	}

	@Override
	public <T extends AttributeModel<T>> Attribute getAttributeInstance(CompilableExpression key,
			CompilableExpression value) {
		return new DynamicAttribute(key, value);
	}

}
