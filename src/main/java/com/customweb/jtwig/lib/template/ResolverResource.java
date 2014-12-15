package com.customweb.jtwig.lib.template;

import java.io.InputStream;

import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.resource.JtwigResource;
import com.lyncode.jtwig.resource.loader.JtwigResourceResolver;

public class ResolverResource implements JtwigResource {
	
	private final JtwigResourceResolver resolver;
	
	private final JtwigResource resource;
	
	public ResolverResource(JtwigResourceResolver resolver, JtwigResource resource) {
		this.resolver = resolver;
		this.resource = resource;
	}

	@Override
	public boolean exists() {
		return resource.exists();
	}

	@Override
	public InputStream retrieve() throws ResourceException {
		return resource.retrieve();
	}

	@Override
	public JtwigResource resolve(String relativePath) throws ResourceException {
		if (relativePath.startsWith("tpl:")) {
			return new ResolverResource(this.resolver, this.resolver.resolve(relativePath.substring(4)));
		} else {
			return new ResolverResource(this.resolver, this.resource.resolve(relativePath));
		}
	}

}
