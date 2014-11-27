package com.customweb.jtwig.lib.template;

import java.util.ArrayList;
import java.util.List;

import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.resource.JtwigResource;

public final class ResourceHandler {
	
	private List<IResourceResolver> resolvers = new ArrayList<IResourceResolver>();
	
	public ResourceHandler() {
		this.addResolver(new DefaultResourceResolver());
	}
	
	public JtwigResource resolve(String resourceName) throws ResourceException {
		for (IResourceResolver resolver : resolvers) {
			try {
				return resolver.resolve(resourceName);
			} catch (ResourceException e) {}
		}
		throw new ResourceException("Resource '" + resourceName + "' not found");
	}
	
	public void addResolver(IResourceResolver resolver) {
		if (!resolvers.contains(resolver)) {
			resolvers.add(0, resolver);
		}
	}
	
	public void removeResolver(IResourceResolver resolver) {
		resolvers.remove(resolver);
	}

}
