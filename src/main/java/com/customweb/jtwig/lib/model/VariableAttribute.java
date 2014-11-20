package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Variable;
import com.lyncode.jtwig.parser.model.JtwigPosition;

public class VariableAttribute extends Attribute {
	private Variable variable;

	public VariableAttribute(CompilableExpression key,
			CompilableExpression value, JtwigPosition position) {
		super(key, value);
		this.variable = new Variable(position, this.getValue());
	}

	public VariableAttribute(String key, CompilableExpression value,
			JtwigPosition position) {
		super(key, value);
		this.variable = new Variable(position, this.getValue());
	}

	public Variable getVariable() {
		return this.variable;
	}
}