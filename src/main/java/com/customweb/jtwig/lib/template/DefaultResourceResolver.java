package com.customweb.jtwig.lib.template;

import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import com.lyncode.jtwig.resource.JtwigResource;

public class DefaultResourceResolver implements IResourceResolver {

	@Override
	public JtwigResource resolve(String resourceName) throws ResourceException {
		JtwigResource resource = new ClasspathJtwigResource("classpath:/" + resourceName + ".twig");
		if (!resource.exists()) {
			throw new ResourceException("Resource '" + resourceName + "' not found");
		}
		return resource;
	}

}
