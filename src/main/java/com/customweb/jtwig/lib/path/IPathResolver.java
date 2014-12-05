package com.customweb.jtwig.lib.path;

/**
 * The path resolver is used to resolve a given relative path to a absolute URL.
 * 
 * @author Simon Schurter
 * 
 */
public interface IPathResolver {

	/**
	 * Resolves the given relative path to a absolute URL. 
	 * 
	 * @param relativePath
	 * @return
	 * @throws ResolverException
	 */
	public String resolve(String relativePath) throws ResolverException;

}
