package com.customweb.jtwig.lib.attribute.model;

import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.render.RenderContext;

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