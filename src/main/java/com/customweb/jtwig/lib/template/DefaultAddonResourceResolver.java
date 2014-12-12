package com.customweb.jtwig.lib.template;

import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import com.lyncode.jtwig.resource.JtwigResource;
import com.lyncode.jtwig.resource.loader.JtwigResourceResolver;

public class DefaultAddonResourceResolver implements JtwigResourceResolver {

	@Override
	public JtwigResource resolve(String viewUrl) {
		return new ClasspathJtwigResource("classpath:/views/addon/" + viewUrl + ".twig");
	}

}
