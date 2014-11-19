package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.addons.AddonModel;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.expressions.model.Variable;
import com.lyncode.jtwig.parser.model.JtwigPosition;

public abstract class AttributeModel<T extends AddonModel<T>> extends AddonModel<T> {

	private AttributeCollection<T> attributeCollection;
	
	public AttributeModel(AttributeCollection<T> attributeCollection) {
		this.attributeCollection = attributeCollection;
	}
	
	public AttributeCollection<T> getAttributeCollection() {
		return this.attributeCollection;
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
