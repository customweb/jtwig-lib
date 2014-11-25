package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.render.RenderContext;
import com.lyncode.jtwig.types.Undefined;

public class VariableAttribute extends ValueAttribute {
	public VariableAttribute(CompilableExpression key, CompilableExpression value) {
		super(key, value);
	}

	public VariableAttribute(String key, CompilableExpression value) {
		super(key, value);
	}
	
	public VariableAttribute(String key, String value) {
		super(key, value);
	}

	public Object getVariable(RenderContext context) {
		Object value = context.map(this.getValue());
		if (value == null || value.equals(Undefined.UNDEFINED)) {
			throw new RuntimeException("The variable for attribute '" + this.getKey() + "' has not been set.");
		}
		return value;
	}
	
	public <T> T getVariable(RenderContext context, Class<T> type) {
		Object value = this.getVariable(context);
		if (!type.isInstance(value)) {
			throw new RuntimeException("The variable for attribute '" + this.getKey() + "' is not of type " + type.getCanonicalName() + ".");
		}
		return type.cast(value);
	}
}