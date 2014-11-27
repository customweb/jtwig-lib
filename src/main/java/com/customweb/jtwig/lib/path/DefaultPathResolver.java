package com.customweb.jtwig.lib.path;

public class DefaultPathResolver implements IPathResolver {

	@Override
	public String resolve(String relativePath) throws ResolverException {
		return relativePath;
	}

}
