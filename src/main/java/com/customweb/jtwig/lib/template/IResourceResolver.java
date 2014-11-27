package com.customweb.jtwig.lib.template;

import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.resource.JtwigResource;

public interface IResourceResolver {

	public JtwigResource resolve(String resourceName) throws ResourceException;
	
}
