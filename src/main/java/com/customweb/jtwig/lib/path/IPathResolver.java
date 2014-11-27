package com.customweb.jtwig.lib.path;


public interface IPathResolver {

	public String resolve(String relativePath) throws ResolverException;
	
}
