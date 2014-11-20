package com.customweb.jtwig.lib.model;

import java.util.ArrayList;
import java.util.List;

import com.lyncode.jtwig.addons.AddonModel;

public abstract class AttributeModel<T extends AttributeModel<T>> extends
		AddonModel<T> {

	private AttributeCollection<T> attributeCollection;

	public AttributeModel() {
		this.attributeCollection = new AttributeCollection<T>();
	}

	public AttributeCollection<T> getAttributeCollection() {
		return this.attributeCollection;
	}

	public AttributeModel<T> validate() {
		for (AttributeDefinition definition : this.getAttributeDefinitions()) {
			definition.validate(this.getAttributeCollection());
		}
		return this;
	}

	public List<AttributeDefinition> getAttributeDefinitions() {
		List<AttributeDefinition> definitions = new ArrayList<AttributeDefinition>();
		definitions.add(new DynamicAttributeDefinition());
		return definitions;
	}
}
