package com.customweb.jtwig.lib.attribute.model;

import java.util.Collection;

import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinition;
import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinitionCollection;
import com.customweb.jtwig.lib.template.AbstractTemplateTag;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.render.RenderContext;

public abstract class AbstractAttributeTag<T extends AbstractAttributeTag<T>> extends AbstractTemplateTag<T> {

	private AttributeCollection attributeCollection;

	public AbstractAttributeTag() {
		this.attributeCollection = new AttributeCollection();
	}

	public AttributeCollection getAttributeCollection() {
		return this.attributeCollection;
	}

	public AbstractAttributeTag<T> validate() {
		for (AttributeDefinition definition : this.getAttributeDefinitions()) {
			definition.validate(this.getAttributeCollection());
		}
		return this;
	}

	public AttributeDefinitionCollection getAttributeDefinitions() {
		return new AttributeDefinitionCollection();
	}

	abstract protected class Compiled extends AbstractTemplateTag<T>.Compiled {
		private final AttributeCollection attributeCollection;

		protected Compiled(Renderable block, Renderable content, AttributeCollection attributeCollection) {
			super(block, content);
			this.attributeCollection = attributeCollection;
		}

		public AttributeCollection getAttributeCollection() {
			return this.attributeCollection;
		}

		public String getAttributeValue(String key) {
			return this.getAttributeCollection().getValueAttribute(key).getValue();
		}
		
		public String getAttributeValue(String key, String defaultValue) {
			if (this.getAttributeCollection().hasAttribute(key)) {
				return this.getAttributeValue(key);
			} else {
				return defaultValue;
			}
		}

		public Collection<DynamicAttribute> getDynamicAttributes() {
			return this.getAttributeCollection().getAttributes(DynamicAttribute.class);
		}
	}

	abstract public class Data extends AbstractTemplateTag<T>.Data {
		private AttributeCollection attributeCollection;

		protected Data(RenderContext context, AttributeCollection attributeCollection) {
			super(context);
			this.attributeCollection = attributeCollection;
		}

		protected AttributeCollection getAttributeCollection() {
			return attributeCollection;
		}

		protected String getAttributeValue(String key) {
			return this.getAttributeCollection().getValueAttribute(key).getValue();
		}
		
		protected String getAttributeValue(String key, String defaultValue) {
			if (this.getAttributeCollection().hasAttribute(key)) {
				return this.getAttributeValue(key);
			} else {
				return defaultValue;
			}
		}

		public Collection<DynamicAttribute> getDynamicAttributes() {
			return this.getAttributeCollection().getAttributes(DynamicAttribute.class);
		}
	}

}
