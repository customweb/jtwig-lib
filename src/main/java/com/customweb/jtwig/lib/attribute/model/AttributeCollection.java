package com.customweb.jtwig.lib.attribute.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.render.RenderContext;

public class AttributeCollection {

	Map<String, Attribute> attributes = new HashMap<String, Attribute>();

	public Attribute getAttribute(String key) {
		return this.attributes.get(key.toLowerCase());
	}

	public ValueAttribute getValueAttribute(String key) {
		return this.getAttribute(key, ValueAttribute.class);
	}

	public <S> S getAttribute(String key, Class<S> type) {
		Attribute attribute = this.getAttribute(key);
		if (type.isInstance(attribute)) {
			return type.cast(attribute);
		}
		throw new NoSuchElementException();
	}

	public boolean hasAttribute(String key) {
		key = key.toLowerCase();
		return this.attributes.containsKey(key)
				&& this.attributes.get(key).isValid();
	}

	public boolean hasAttribute(String key, Class<?> type) {
		return this.hasAttribute(key) && type.isInstance(this.getAttribute(key));
	}

	public Collection<Attribute> getAttributes() {
		return this.attributes.values();
	}

	public <S> Collection<S> getAttributes(Class<S> type) {
		List<S> attributes = new ArrayList<S>();
		for (Attribute attribute : this.attributes.values()) {
			if (type.isInstance(attribute)) {
				attributes.add(type.cast(attribute));
			}
		}
		return attributes;
	}

	public AttributeCollection addAttribute(Attribute attribute) {
		if (attribute != null) {
			attributes.put(attribute.getKey(), attribute);
		}
		return this;
	}
	
	public void compile(CompileContext context) throws CompileException {
		for (Attribute attribute : this.getAttributes()) {
			attribute.compile(context);
		}
	}
	
	public void render(RenderContext context) throws RenderException {
		for (Attribute attribute : this.getAttributes()) {
			attribute.render(context);
		}
	}

}
