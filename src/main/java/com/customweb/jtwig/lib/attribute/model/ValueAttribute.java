package com.customweb.jtwig.lib.attribute.model;

import java.io.ByteArrayOutputStream;

import org.jtwig.Environment;
import org.jtwig.compile.CompileContext;
import org.jtwig.content.api.Renderable;
import org.jtwig.exception.CompileException;
import org.jtwig.exception.ParseException;
import org.jtwig.exception.RenderException;
import org.jtwig.exception.ResourceException;
import org.jtwig.expressions.api.CompilableExpression;
import org.jtwig.expressions.model.Constant;
import org.jtwig.loader.impl.StringLoader;
import org.jtwig.render.RenderContext;

abstract public class ValueAttribute extends Attribute {
	private Environment environment;
	
	private String value;
	private String compilableValue;
	private Renderable renderableValue;

	@SuppressWarnings("unchecked")
	public ValueAttribute(CompilableExpression key, CompilableExpression value, Environment environment) {
		super(key);
		this.environment = environment;
		this.compilableValue = ((Constant<String>) value).as(String.class);
	}

	@SuppressWarnings("unchecked")
	public ValueAttribute(String key, CompilableExpression value, Environment environment) {
		super(key);
		this.environment = environment;
		this.compilableValue = ((Constant<String>) value).as(String.class);
	}

	public ValueAttribute(String key, String value) {
		super(key);
		this.value = value;
	}

	public String getValue() {
		if (this.value == null) {
			throw new RuntimeException("The value for attribute '" + this.getKey() + "' has not been set.");
		}
		return value;
	}

	@Override
	public String toString() {
		return getKey() + "=\"" + getValue() + "\"";
	}

	@Override
	public boolean isValid() {
		return (this.compilableValue != null && !this.compilableValue.isEmpty())
				|| (this.value != null && !this.value.isEmpty());
	}

	@Override
	public void compile(CompileContext context) throws CompileException {
		if (this.compilableValue == null && this.value == null) {
			throw new CompileException("The value for attribute '" + this.getKey() + "' has not been set.");
		} else if (this.compilableValue != null) {
			StringLoader.StringResource resource = new StringLoader.StringResource(this.compilableValue);
			try {
				this.renderableValue = this.environment.parse(resource).compile(context);
			} catch (ParseException | ResourceException e) {
				throw new CompileException(e);
			}
		}
	}

	@Override
	public void render(RenderContext context) throws RenderException {
		if (this.renderableValue == null && this.value == null) {
			throw new RenderException("The value for attribute '" + this.getKey() + "' has not been set.");
		} else if (this.renderableValue != null) {
			ByteArrayOutputStream contentRenderStream = new ByteArrayOutputStream();
			this.renderableValue.render(context.newRenderContext(contentRenderStream));
			this.value = contentRenderStream.toString();
		}
	}
}
