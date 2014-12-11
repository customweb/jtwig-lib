package com.customweb.jtwig.lib.model;

import com.lyncode.jtwig.util.ObjectExtractor.ExtractException;

abstract public class ObjectExtractor {
	
	public static Object extract(Object context, String expression) throws ExtractException {
		for (String part : expression.split("\\.")) {
			context = new com.lyncode.jtwig.util.ObjectExtractor(context).extract(part);
			if (context == null) {
				return null;
			}
		}
		return context;
	}
	
}
