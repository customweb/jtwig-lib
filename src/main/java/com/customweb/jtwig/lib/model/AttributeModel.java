package com.customweb.jtwig.lib.model;

import java.util.Collection;

import com.lyncode.jtwig.addons.AddonModel;
import com.lyncode.jtwig.content.api.Renderable;

public abstract class AttributeModel<T extends AttributeModel<T>> extends AddonModel<T> {

	private AttributeCollection attributeCollection;

	public AttributeModel() {
		this.attributeCollection = new AttributeCollection();
	}

	public AttributeCollection getAttributeCollection() {
		return this.attributeCollection;
	}

	public AttributeModel<T> validate() {
		for (AttributeDefinition definition : this.getAttributeDefinitions()) {
			definition.validate(this.getAttributeCollection());
		}
		return this;
	}

	public AttributeDefinitionCollection getAttributeDefinitions() {
		return new AttributeDefinitionCollection();
	}
	
	abstract protected class AbstractAttributeModelCompiled implements Renderable {
		private final Renderable content;
		private final AttributeCollection attributeCollection;

		protected AbstractAttributeModelCompiled(Renderable content, AttributeCollection attributeCollection) {
			this.content = content;
			this.attributeCollection = attributeCollection;
		}

		public Renderable getContent() {
			return this.content;
		}
		
		public AttributeCollection getAttributeCollection() {
			return this.attributeCollection;
		}
		
		public String getAttributeValue(String key) {
			return this.getAttributeCollection().getValueAttribute(key).getValue();
		}
		
		public Collection<DynamicAttribute> getDynamicAttributes() {
			return this.getAttributeCollection().getAttributes(DynamicAttribute.class);
		}
		
		public String concatDynamicAttributes() {
			StringBuilder builder = new StringBuilder();
			for (Attribute attribute : this.getDynamicAttributes()) {
				builder.append(" ").append(attribute.toString());
			}
			return builder.toString();
		}
	}
	
}
