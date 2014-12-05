package com.customweb.jtwig.lib.attribute.model;

import java.io.ByteArrayOutputStream;

import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.expressions.api.CompilableExpression;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.parser.config.ParserConfiguration;
import com.lyncode.jtwig.parser.parboiled.JtwigContentParser;
import com.lyncode.jtwig.render.RenderContext;
import com.lyncode.jtwig.resource.StringJtwigResource;

abstract public class ValueAttribute extends Attribute {
	private ParserConfiguration configuration;
	
	private String value;
	private String compilableValue;
	private Renderable renderableValue;

	@SuppressWarnings("unchecked")
	public ValueAttribute(CompilableExpression key, CompilableExpression value, ParserConfiguration configuration) {
		super(key);
		this.configuration = configuration;
		this.compilableValue = ((Constant<String>) value).as(String.class);
	}

	@SuppressWarnings("unchecked")
	public ValueAttribute(String key, CompilableExpression value, ParserConfiguration configuration) {
		super(key);
		this.configuration = configuration;
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
			StringJtwigResource resource = new StringJtwigResource(this.compilableValue);
			try {
				this.renderableValue = JtwigContentParser.parse(JtwigContentParser.newParser(resource, this.configuration), resource).compile(context);
			} catch (ParseException e) {
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
