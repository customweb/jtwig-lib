jtwig-lib
=========
This library provides basic functionality for [Jtwig template engine](http://jtwig.org/) addons.

## Attributes ##
Add key-value attributes to Jtwig tags.

```twig
{% tag key="value" %}{% endtag %}
```

### Attribute Types ###
There are some predefined attribute types. The library can however be easily extended by further types.

#### NamedAttribute ####
This is the basic attribute type where the key is predefined.

```java
NamedAttributeDefinition(String key, boolean mandatory);
```

```twig
{% tag key="value" %}{% endtag %}
```

#### EmptyAttribute ####
This attribute type demands no value. It can be used as boolean attribute.

```java
EmptyAttributeDefinition(String key);
```

```twig
{% tag key %}{% endtag %}
```

#### VariableAttribute ####
This attribute demands a variable name that is matched to an entry in the JtwigModelMap.

```java
VariableAttributeDefinition(String key, boolean mandatory);
```

```twig
{% tag key="variableName" %}{% endtag %}
```

#### DynamicAttribute ####
This attribute is a special one. It matches all key-value attributes that are not specifically defined.

```java
DynamicAttributeDefinition();
```

```twig
{% tag somekey="value" anotherkey="value" %}{% endtag %}
```

It is however possible to define keys that are not allowed.

### Usage ###
Create an addon class that extends the `com.customweb.jtwig.lib.attribute.AttributeAddon<T>` and a model class that extends `com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag<T>`.

```java
public class MyTagAddon extends AttributeAddon<MyTag> {
  public MyTagAddon(JtwigResource resource, ParserConfiguration configuration) {
		super(resource, configuration);
	}
	
  @Override
	protected String keyword() {
		return "mytag";
	}
	
	@Override
	public MyTag instance() {
		return new MyTag();
	}
}

public class MyTag extends AbstractAttributeTag<MyTag> {
  @Override
	public Renderable compile(CompileContext context) throws CompileException {
		return new Compiled(super.compile(context), this.getAttributeCollection());
	}
	
	private class Compiled extends AbstractAttributeModelCompiled {
	  protected Compiled(Renderable content, AttributeCollection attributeCollection) {
			super(content, attributeCollection);
		}
		
		@Override
		public void render(RenderContext context) throws RenderException {
		  ...
		}
	}
}
```
Override the `getAttributeDefinitions()` method to specify the tag's attributes.

```java
@Override
public AttributeDefinitionCollection getAttributeDefinitions() {
	AttributeDefinitionCollection attributeDefinitions = super.getAttributeDefinitions();
	attributeDefinitions.add(new NamedAttributeDefinition("action", true));
	attributeDefinitions.add(new VariableAttributeDefinition("model", false));
	attributeDefinitions.add(new EmptyAttributeDefinition("disabled"));
	return attributeDefinitions;
}
```

Dynamic attributes are allowed by default, but you can disable them.

```java
attributeDefinitions.isAllowDynamicAttributes(false);
```
