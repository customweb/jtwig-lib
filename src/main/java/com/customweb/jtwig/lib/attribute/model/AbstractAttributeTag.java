package com.customweb.jtwig.lib.attribute.model;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinition;
import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinitionCollection;
import com.lyncode.jtwig.addons.AddonModel;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.render.RenderContext;

public abstract class AbstractAttributeTag<T extends AbstractAttributeTag<T>> extends AddonModel<T> {

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
		
		public String renderContentAsString(RenderContext context) throws RenderException {
			ByteArrayOutputStream contentRenderStream = new ByteArrayOutputStream();
			this.getContent().render(context.newRenderContext(contentRenderStream));
			return contentRenderStream.toString();
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
	}

	abstract public class AbstractAttributeModelData {
		private RenderContext context;
		private AttributeCollection attributeCollection;

		protected AbstractAttributeModelData(RenderContext context, AttributeCollection attributeCollection) {
			this.context = context;
			this.attributeCollection = attributeCollection;
		}

		protected RenderContext getContext() {
			return this.context;
		}

		protected AttributeCollection getAttributeCollection() {
			return attributeCollection;
		}

		protected String getAttributeValue(String key) {
			return this.getAttributeCollection().getValueAttribute(key).getValue();
		}

		public Collection<DynamicAttribute> getDynamicAttributes() {
			return this.getAttributeCollection().getAttributes(DynamicAttribute.class);
		}
	}

}
