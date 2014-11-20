package com.customweb.jtwig.lib.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class AttributeCollection<T extends AttributeModel<T>> {

	Map<String, Attribute> attributes = new HashMap<String, Attribute>();

	public Attribute getAttribute(String key) {
		return this.attributes.get(key.toLowerCase());
	}

	public <S> S getAttribute(String key, Class<S> type) {
		Attribute attribute = this.getAttribute(key);
		if (type.isInstance(attribute)) {
			return type.cast(attribute);
		}
		throw new NoSuchElementException();
	}

	public boolean hasAttribute(String key) {
		return this.attributes.containsKey(key);
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

	public AttributeCollection<T> addAttribute(Attribute attribute) {
		attributes.put(attribute.getKey(), attribute);
		return this;
	}

}
