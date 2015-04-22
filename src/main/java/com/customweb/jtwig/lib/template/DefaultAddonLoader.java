package com.customweb.jtwig.lib.template;

import org.jtwig.exception.ResourceException;
import org.jtwig.loader.Loader;
import org.jtwig.loader.impl.ClasspathLoader;

public class DefaultAddonLoader extends Loader {
	
	private Loader internalLoader = new ClasspathLoader();
	
	@Override
    public boolean exists(final String name) throws ResourceException {
		return this.internalLoader.exists(this.getAddonResourcePath(name));
	}
	
	@Override
    public Loader.Resource get(final String name) throws ResourceException {
		return this.internalLoader.get(this.getAddonResourcePath(name));
	}
	
	protected String getAddonResourcePath(String name) {
		return "classpath:/views/addon/" + name + ".twig";
	}
	
}
