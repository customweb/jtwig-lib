package com.customweb.jtwig.lib.attribute.model;

import org.jtwig.compile.CompileContext;
import org.jtwig.exception.CompileException;
import org.jtwig.exception.RenderException;
import org.jtwig.expressions.api.CompilableExpression;
import org.jtwig.expressions.model.Constant;
import org.jtwig.render.RenderContext;

abstract public class Attribute {
	private String key;

	@SuppressWarnings("unchecked")
	public Attribute(CompilableExpression key) {
		this((String) ((Constant<String>) key).as(String.class).toLowerCase());
	}

	public Attribute(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String toString() {
		return getKey();
	}
	
	public boolean isValid() {
		return true;
	}

	public void compile(CompileContext context) throws CompileException {
	}

	public void render(RenderContext context) throws RenderException {
	}
}