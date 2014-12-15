package com.customweb.jtwig.lib.template;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lyncode.jtwig.resource.JtwigResource;

public class ChainedResourceResolverTest {

	@Test
	public void resolveRelative() {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		JtwigResource resource = resolver.resolve("resolve-relative");
		assertTrue(resource instanceof ResolverResource);
		assertTrue(resource.exists());
	}
	
	@Test(expected = RuntimeException.class)
	public void notFoundResource() {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		resolver.resolve("not-existing");
	}

}
