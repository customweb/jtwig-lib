package com.customweb.jtwig.lib.attribute.model;

import org.jtwig.Environment;
import org.jtwig.exception.RenderException;
import org.jtwig.expressions.api.CompilableExpression;
import org.jtwig.render.RenderContext;
import org.jtwig.types.Undefined;
import org.jtwig.util.ObjectExtractor.ExtractException;

import com.customweb.jtwig.lib.model.ObjectExtractor;

public class VariableAttribute extends ValueAttribute {
	private Object variable;

	public VariableAttribute(CompilableExpression key, CompilableExpression value, Environment environment) {
		super(key, value, environment);
	}

	public VariableAttribute(String key, CompilableExpression value, Environment environment) {
		super(key, value, environment);
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

		String path = this.getValue();
		String beanName;
		String expression;
		Object variable = null;

		int dotPos = path.indexOf('.');
		if (dotPos == -1) {
			beanName = path;
			expression = null;
		} else {
			beanName = path.substring(0, dotPos);
			expression = path.substring(dotPos + 1);
		}
		
		Object target = context.map(beanName);
		if (target == null || target.equals(Undefined.UNDEFINED)) {
			throw new IllegalStateException("No target object for bean name '" + beanName + "' available as model map attribute.");
		}
		if (expression != null) {
			try {
				variable = ObjectExtractor.extract(target, expression);
			} catch (ExtractException e) {
				throw new RenderException(e);
			}
		} else {
			variable = target;
		}
		this.variable = variable;
	}
}