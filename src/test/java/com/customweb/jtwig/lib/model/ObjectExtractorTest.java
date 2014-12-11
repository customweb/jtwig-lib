package com.customweb.jtwig.lib.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lyncode.jtwig.util.ObjectExtractor.ExtractException;

public class ObjectExtractorTest {

	@Test
	public void shouldExtractFromField() throws ExtractException {
		Object result = ObjectExtractor.extract(new A(), "a");
		assertTrue(result instanceof String);
		assertEquals("a", result);
	}

	@Test
	public void shouldExtractFromChildField() throws ExtractException {
		B b = new B();
		Object result = ObjectExtractor.extract(b, "a");
		assertTrue(result instanceof A);
		assertEquals(b.a, result);

		result = ObjectExtractor.extract(new B(), "a.a");
		assertTrue(result instanceof String);
		assertEquals("a", result);
	}

	@Test
	public void shouldExtractFromInheritedField() throws ExtractException {
		Object result = ObjectExtractor.extract(new C(), "a");
		assertTrue(result instanceof String);
		assertEquals("a", result);
	}

	@Test
	public void shouldExtractFromMethod() throws ExtractException {
		D d = new D();

		Object result = ObjectExtractor.extract(d, "d1");
		assertTrue(result instanceof String);
		assertEquals("d1", result);

		// get
		result = ObjectExtractor.extract(d, "d2");
		assertTrue(result instanceof String);
		assertEquals("d2", result);

		// is
		result = ObjectExtractor.extract(d, "d3");
		assertTrue(result instanceof String);
		assertEquals("d3", result);

		// has
		result = ObjectExtractor.extract(d, "d4");
		assertTrue(result instanceof String);
		assertEquals("d4", result);
	}

	@Test
	public void shouldExtractFromInheritedMethod() throws ExtractException {
		E e = new E();

		Object result = ObjectExtractor.extract(e, "d1");
		assertTrue(result instanceof String);
		assertEquals("d1", result);
	}

	public static class A {
		public String a = "a";
	}

	public static class B {
		public String b = "b";
		public A a = new A();
	}

	public static class C extends A {
	}

	public static class D {
		public String d1() {
			return "d1";
		}

		public String getD2() {
			return "d2";
		}

		public String isD3() {
			return "d3";
		}

		public String hasD4() {
			return "d4";
		}
	}

	public static class E extends D {
	}

}
