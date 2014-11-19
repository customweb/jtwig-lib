package com.customweb.jtwig.lib.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customweb.jtwig.lib.model.AttributeModel.Attribute;
import com.customweb.jtwig.lib.model.AttributeModel.DynamicAttribute;

public class AttributeCollection<T extends AttributeModel<T>> {

	Map<String, Attribute> attributes = new HashMap<String, Attribute>();
	
	public Attribute getAttribute(String key) {
		return this.attributes.get(key.toLowerCase());
	}
	
	public Collection<Attribute> getAttributes() {
		return this.attributes.values();
	}
	
	public Collection<Attribute> getDynamicAttributes() {
		List<Attribute> dynamicAttributes = new ArrayList<Attribute>();
		for (Attribute attribute : this.attributes.values()) {
			if (attribute instanceof DynamicAttribute) {
				dynamicAttributes.add(attribute);
			}
		}
		return dynamicAttributes;
	}
	
	public AttributeCollection<T> addAttribute(Attribute attribute) {
		attributes.put(attribute.getKey(), attribute);
		return this;
	}
	
}
