package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.parser.config.ParserConfiguration;
import com.lyncode.jtwig.render.RenderContext;
import com.lyncode.jtwig.types.Undefined;

public class VariableAttribute extends ValueAttribute {
	private Object variable;
	
	public VariableAttribute(CompilableExpression key, CompilableExpression value, ParserConfiguration configuration) {
		super(key, value, configuration);
	}

	public VariableAttribute(String key, CompilableExpression value, ParserConfiguration configuration) {
		super(key, value, configuration);
	}
	
	public VariableAttribute(String key, String value) {
		super(key, value);
	}

	public Object getVariable() {
		if (this.variable == null) {
			throw new RuntimeException("The variable for attribute '" + this.getKey() + "' has not been set.");
		}
		return this.variable;
	}
	
	public <T> T getVariable(Class<T> type) {
		return type.cast(this.getVariable());
	}

	@Override
	public void render(RenderContext context) throws RenderException {
		super.render(context);
		
		Object variable = context.map(this.getValue());
		if (variable == null || variable.equals(Undefined.UNDEFINED)) {
			throw new RuntimeException("The variable for attribute '" + this.getKey() + "' has not been set.");
		}
		this.variable = variable;
	}
}