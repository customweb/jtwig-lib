package com.customweb.jtwig.lib.attribute;

import java.util.ArrayList;
import java.util.List;

import org.jtwig.Environment;
import org.jtwig.addons.Addon;
import org.jtwig.expressions.model.Constant;
import org.jtwig.loader.Loader;
import org.jtwig.parser.model.JtwigSymbol;
import org.jtwig.parser.parboiled.JtwigBasicParser;
import org.parboiled.BaseParser;
import org.parboiled.Rule;

import com.customweb.jtwig.lib.attribute.model.AbstractAttributeTag;
import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinition;

public abstract class AttributeAddon<T extends AbstractAttributeTag<T>> extends Addon {
	
	private Environment environment;

	public AttributeAddon(Loader.Resource resource, Environment environment) {
		super(resource, environment);
		this.environment = environment;
	}

	abstract protected String keyword();

	@Override
	public java.lang.String beginKeyword() {
		return keyword();
	}

	@Override
	public java.lang.String endKeyword() {
		return "end" + keyword();
	}
	
	public Environment parserConfiguration() {
		return this.environment;
	}

	@Override
	abstract public T instance();

	@Override
	public Rule startRule() {
		return Optional(Sequence(push(instance()), ZeroOrMore(attributeRules()), validate()));
	}

	@SuppressWarnings("unchecked")
	protected Rule validate() {
		return Sequence(JtwigBasicParser.EMPTY, action(((T) expressionParser().peek(0)).validate()));
	}

	protected Rule attributeRules() {
		List<Rule> rules = new ArrayList<Rule>();
		for (AttributeDefinition definition : instance().getAttributeDefinitions()) {
			rules.add(attribute(definition));
		}
		return FirstOf(rules.toArray());
	}

	@SuppressWarnings("unchecked")
	public Rule attribute(AttributeDefinition definition) {
		return Sequence(
				definition.getKeyRule(this),
				expressionParser().push(new Constant<>(match())),
				FirstOf(Sequence(
						basicParser().symbol(JtwigSymbol.ATTR),
						FirstOf(string(basicParser().symbol(JtwigSymbol.QUOTE)),
								string(basicParser().symbol(JtwigSymbol.DOUBLE_QUOTE))),
						expressionParser().push(new Constant<>(basicParser().pop())),
						action(((T) expressionParser().peek(2)).getAttributeCollection().addAttribute(
								definition.getAttributeInstance(expressionParser().pop(1), expressionParser().pop(0), parserConfiguration())))),
						action(((T) expressionParser().peek(1)).getAttributeCollection().addAttribute(
								definition.getAttributeInstance(expressionParser().pop(0), null, parserConfiguration())))), basicParser()
						.spacing());
	}

	public Rule string(Rule delimiter) {
		return Sequence(
				delimiter,
				basicParser().push(""),
				ZeroOrMore(Sequence(TestNot(delimiter), BaseParser.ANY,
						basicParser().push(basicParser().pop() + match()))), delimiter);
	}
	
	public Rule identifier() {
		return Sequence(
				TestNot(basicParser().anyKeyword()),
				FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), '_'),
                ZeroOrMore(FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'), CharRange('0', '9'), '_', '-'))
			);
	}

}
