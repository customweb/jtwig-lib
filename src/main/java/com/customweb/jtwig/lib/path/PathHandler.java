package com.customweb.jtwig.lib.path;

import java.util.ArrayList;
import java.util.List;

import com.lyncode.jtwig.exception.ResourceException;

public class PathHandler {

	private List<IPathResolver> resolvers = new ArrayList<IPathResolver>();
	
	public PathHandler() {
		this.addResolver(new DefaultPathResolver());
	}
	
	public String resolve(String relativePath) throws ResourceException {
		for (IPathResolver resolver : resolvers) {
			try {
				return resolver.resolve(relativePath);
			} catch (ResolverException e) {}
		}
		throw new ResourceException("Path '" + relativePath + "' could not be resolved.");
	}
	
	public void addResolver(IPathResolver resolver) {
		if (!resolvers.contains(resolver)) {
			resolvers.add(0, resolver);
		}
	}
	
	public void removeResolver(IPathResolver resolver) {
		resolvers.remove(resolver);
	}
	
}
