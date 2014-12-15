package com.customweb.jtwig.lib.template;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import com.lyncode.jtwig.resource.JtwigResource;

public class ResolverResourceTest {

	@Test(expected = RuntimeException.class)
	public void resolveMissingTemplate() throws ResourceException {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		JtwigResource parentResource = new ClasspathJtwigResource("classpath:/views/addon/parent/resource.twig");
		JtwigResource resource = new ResolverResource(resolver, parentResource);
		resource.resolve("tpl:../resolve-relative");
	}
	
	@Test
	public void resolveTemplate() throws ResourceException {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		JtwigResource parentResource = new ClasspathJtwigResource("classpath:/views/addon/parent/resource.twig");
		JtwigResource resource = new ResolverResource(resolver, parentResource);
		JtwigResource resolvedResource = resource.resolve("tpl:resolve-relative");
		assertTrue(resolvedResource instanceof ResolverResource);
		assertTrue(resolvedResource.exists());
	}
	
	@Test
	public void resolveMissingRelative() throws ResourceException {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		JtwigResource parentResource = new ClasspathJtwigResource("classpath:/views/addon/parent/resource.twig");
		JtwigResource resource = new ResolverResource(resolver, parentResource);
		JtwigResource resolvedResource = resource.resolve("resolve-relative");
		assertTrue(resolvedResource instanceof ResolverResource);
		assertTrue(!resolvedResource.exists());
	}
	
	@Test
	public void resolveRelative() throws ResourceException {
		ChainedResourceResolver resolver = new ChainedResourceResolver().addResolver(new DefaultAddonResourceResolver());
		JtwigResource parentResource = new ClasspathJtwigResource("classpath:/views/addon/parent/resource.twig");
		JtwigResource resource = new ResolverResource(resolver, parentResource);
		JtwigResource resolvedResource = resource.resolve("../resolve-relative.twig");
		assertTrue(resolvedResource instanceof ResolverResource);
		assertTrue(resolvedResource.exists());
	}

}
