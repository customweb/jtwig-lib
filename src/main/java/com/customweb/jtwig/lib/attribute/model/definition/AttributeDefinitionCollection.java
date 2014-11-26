package com.customweb.jtwig.lib.attribute.model.definition;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class AttributeDefinitionCollection implements Iterable<AttributeDefinition> {

	Map<String, AttributeDefinition> attributeDefinitions = new HashMap<String, AttributeDefinition>();
	DynamicAttributeDefinition dynamicAttributeDefinition = new DynamicAttributeDefinition();
	boolean allowDynamicAttributes = true;
	
	public void add(AttributeDefinition attributeDefinition) {
		if (attributeDefinition instanceof DynamicAttributeDefinition) {
			this.dynamicAttributeDefinition.addDisallowedKeys(((DynamicAttributeDefinition) attributeDefinition).getDisallowedKeys());
		} else if (attributeDefinition instanceof NamedAttributeDefinition) {
			this.attributeDefinitions.put(((NamedAttributeDefinition) attributeDefinition).getKey(), attributeDefinition);
		}
	}
	
	public DynamicAttributeDefinition getDynamicAttributeDefinition() {
		return this.dynamicAttributeDefinition;
	}
	
	public void setAllowDynamicAttributes(boolean allowDynamicAttributes) {
		this.allowDynamicAttributes = allowDynamicAttributes;
	}

	public boolean isAllowDynamicAttributes() {
		return this.allowDynamicAttributes;
	}
	
	public List<AttributeDefinition> getAttributeDefinitions() {
		List<AttributeDefinition> attributeDefinitions = Lists.newArrayList(this.attributeDefinitions.values());
		if (this.isAllowDynamicAttributes()) {
			attributeDefinitions.add(this.getDynamicAttributeDefinition());
		}
		return attributeDefinitions;
	}

	@Override
	public Iterator<AttributeDefinition> iterator() {
		return this.getAttributeDefinitions().iterator();
	}
	
}
