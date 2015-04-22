package com.customweb.jtwig.lib.model;

import java.io.ByteArrayOutputStream;

import org.jtwig.Environment;
import org.jtwig.JtwigModelMap;
import org.jtwig.configuration.JtwigConfigurationBuilder;
import org.jtwig.render.RenderContext;
import org.jtwig.util.ObjectExtractor.ExtractException;

abstract public class ObjectExtractor {
	
	public static Object extract(Object context, String expression) throws ExtractException {
		RenderContext renderContext = RenderContext.create(new Environment(JtwigConfigurationBuilder.newConfiguration().build()), new JtwigModelMap(), new ByteArrayOutputStream());
		for (String part : expression.split("\\.")) {
			context = new org.jtwig.util.ObjectExtractor(renderContext, context).extract(part);
			if (context == null) {
				return null;
			}
		}
		return context;
	}
	
}
