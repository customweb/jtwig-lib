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
