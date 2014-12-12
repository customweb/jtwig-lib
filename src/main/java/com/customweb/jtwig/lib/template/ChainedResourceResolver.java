package com.customweb.jtwig.lib.template;

import java.util.ArrayList;
import java.util.List;

import com.lyncode.jtwig.resource.JtwigResource;
import com.lyncode.jtwig.resource.loader.JtwigResourceResolver;

public class ChainedResourceResolver implements JtwigResourceResolver {
	
	private List<JtwigResourceResolver> resolvers = new ArrayList<JtwigResourceResolver>();
	
	@Override
	public JtwigResource resolve(String viewUrl) {
		for (JtwigResourceResolver resolver : resolvers) {
			JtwigResource resource = resolver.resolve(viewUrl);
			if (resource.exists()) {
				return new ResolverResource(this, resource);
			}
		}
		throw new RuntimeException("Resource '" + viewUrl + "' not found");
	}
	
	public ChainedResourceResolver addResolver(JtwigResourceResolver... resolvers) {
		for (JtwigResourceResolver resolver : resolvers) {
			this.resolvers.add(resolver);
		}
		return this;
	}
	
	public ChainedResourceResolver clear() {
		this.resolvers.clear();
		return this;
	}

}
