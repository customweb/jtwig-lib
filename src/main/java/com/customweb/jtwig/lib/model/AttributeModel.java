package com.customweb.jtwig.lib.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyncode.jtwig.addons.AddonModel;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.expressions.model.Variable;
import com.lyncode.jtwig.parser.model.JtwigPosition;

public abstract class AttributeModel<T extends AddonModel<?>> extends AddonModel<T> {

	Map<String, Attribute> attributes = new HashMap<String, Attribute>();
	
	public Attribute getAttribute(String key) {
		return this.attributes.get(key.toLowerCase());
	}
	
	public Collection<Attribute> getAttributes() {
		return this.attributes.values();
	}
	
	public Collection<Attribute> getDynamicAttributes() {
		List<Attribute> dynamicAttributes = new ArrayList<Attribute>();
		for (Attribute attribute : this.attributes.values()) {
			if (attribute instanceof DynamicAttribute) {
				dynamicAttributes.add(attribute);
			}
		}
		return dynamicAttributes;
	}
	
	public AttributeModel<T> addAttribute(Attribute attribute) {
		attributes.put(attribute.getKey(), attribute);
		return this;
	}
	
	public static class Attribute {
		private String key;
		private String value;
		
		@SuppressWarnings("unchecked")
		public Attribute(CompilableExpression key, CompilableExpression value) {
			this((String) ((Constant<String>) key).as(String.class).toLowerCase(), value);
		}
		
		@SuppressWarnings("unchecked")
		public Attribute(String key, CompilableExpression value) {
			this.key = key.toLowerCase();
			this.value = (String) ((Constant<String>) value).as(String.class);
		}
		
		public String getKey() {
			return key;
		}
		
		public String getValue() {
			return value;
		}
		
		public String toString() {
			return getKey() + "=\"" + getValue() + "\"";
		}
	}
	
	public static class DynamicAttribute extends Attribute {
		public DynamicAttribute(CompilableExpression key,
				CompilableExpression value) {
			super(key, value);
		}
	}
	
	public static class VariableAttribute extends Attribute {
		private Variable variable;
		
		public VariableAttribute(CompilableExpression key,
				CompilableExpression value, JtwigPosition position) {
			super(key, value);
			this.variable = new Variable(position, this.getValue());
		}
		
		public VariableAttribute(String key,
				CompilableExpression value, JtwigPosition position) {
			super(key, value);
			this.variable = new Variable(position, this.getValue());
		}
		
		public Variable getVariable() {
			return this.variable;
		}
	}
	
}
