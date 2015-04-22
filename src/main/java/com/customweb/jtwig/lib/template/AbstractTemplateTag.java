package com.customweb.jtwig.lib.template;

import java.io.ByteArrayOutputStream;

import org.jtwig.addons.AddonModel;
import org.jtwig.compile.CompileContext;
import org.jtwig.content.api.Renderable;
import org.jtwig.exception.RenderException;
import org.jtwig.exception.ResourceException;
import org.jtwig.loader.Loader;
import org.jtwig.loader.impl.EmptyLoader;
import org.jtwig.render.RenderContext;
import org.jtwig.types.Undefined;

public abstract class AbstractTemplateTag<T extends AbstractTemplateTag<T>> extends AddonModel<T> {
	
	private final Loader defaultAddonLoader = new DefaultAddonLoader();
	
	protected final Loader.Resource retrieveResource(CompileContext context, String viewUrl) throws ResourceException {
		try {
			Loader.Resource resource = context.environment().load("tpl:" + viewUrl);
			if (!(resource instanceof EmptyLoader.NoResource)) {
				return resource;
			}
		} catch (ResourceException e) {}
		if (defaultAddonLoader.exists(viewUrl)) {
			return defaultAddonLoader.get(viewUrl);
		}
		throw new ResourceException("Resource '" + viewUrl + "' not found");
	}
	
	abstract protected class Compiled implements Renderable {
		private final Renderable block;
		private final Renderable content;

		protected Compiled(Renderable block, Renderable content) {
			this.content = content;
			this.block = block;
		}

		public Renderable getBlock() {
			return this.block;
		}

		public Renderable getContent() {
			return this.content;
		}

		public String renderContentAsString(RenderContext context) throws RenderException {
			ByteArrayOutputStream contentRenderStream = new ByteArrayOutputStream();
			this.getContent().render(context.newRenderContext(contentRenderStream));
			return contentRenderStream.toString();
		}

		public void prepareContext(RenderContext context) throws RenderException {
			
		}

		@Override
		public void render(RenderContext context) throws RenderException {
			RenderContext isolatedContext = this.isolatedModel(context);
			this.prepareContext(isolatedContext);
			this.getBlock().render(isolatedContext);
		}
		
		public RenderContext isolatedModel(RenderContext context) {
			RenderContext isolatedContext = context.isolatedModel();
			if (context.map("topContext") != null && !context.map("topContext").equals(Undefined.UNDEFINED)) {
				isolatedContext.with("topContext", context.map("topContext"));
			} else {
				isolatedContext.with("topContext", context);
			}
			return isolatedContext;
		}
	}

	abstract public class Data {
		private RenderContext context;

		protected Data(RenderContext context) {
			this.context = context;
		}

		protected RenderContext getContext() {
			return this.context;
		}
	}

}
