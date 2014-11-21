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
		if (value.equals(Undefined.UNDEFINED)) {
			throw new RuntimeException("The variable for attribute '" + this.getKey() + "' has not been set,");
		}
		return value;
	}
}