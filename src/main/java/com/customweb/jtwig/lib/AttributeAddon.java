package com.customweb.jtwig.lib;

import org.parboiled.Rule;

import com.customweb.jtwig.lib.model.AttributeModel;
import com.lyncode.jtwig.addons.Addon;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.parser.config.ParserConfiguration;
import com.lyncode.jtwig.parser.model.JtwigSymbol;
import com.lyncode.jtwig.resource.JtwigResource;

public abstract class AttributeAddon<T extends AttributeModel<?>> extends Addon {

	public AttributeAddon(JtwigResource resource, ParserConfiguration configuration) {
		super(resource, configuration);
	}
	
	@SuppressWarnings("unchecked")
	public Rule attribute(String key) {
		return Sequence(
			IgnoreCase(key).label('\'' + key.toLowerCase() + '\''),
			basicParser().symbol(JtwigSymbol.ATTR),
			FirstOf(
				string(basicParser().symbol(JtwigSymbol.QUOTE)),
				string(basicParser().symbol(JtwigSymbol.DOUBLE_QUOTE))
			),
			expressionParser().push(new Constant<>(basicParser().pop())),
			action(((T)expressionParser().peek(2)).addAttribute(new T.Attribute(key, expressionParser().pop(0)))),
			basicParser().spacing()
        );
	}
	
	@SuppressWarnings("unchecked")
	public Rule variableAttribute(String key) {
		return Sequence(
			IgnoreCase(key).label('\'' + key.toLowerCase() + '\''),
			basicParser().symbol(JtwigSymbol.ATTR),
			FirstOf(
				string(basicParser().symbol(JtwigSymbol.QUOTE)),
				string(basicParser().symbol(JtwigSymbol.DOUBLE_QUOTE))
			),
			basicParser().identifier(),
			expressionParser().push(new Constant<>(basicParser().pop())),
			action(((T)expressionParser().peek(2)).addAttribute(new T.VariableAttribute(key, expressionParser().pop(0), currentPosition()))),
			basicParser().spacing()
        );
	}

	@SuppressWarnings("unchecked")
	public Rule dynamicAttribute() {
		return Sequence(
			basicParser().identifier(),
			expressionParser().push(new Constant<>(match())),
			basicParser().symbol(JtwigSymbol.ATTR),
			FirstOf(
				string(basicParser().symbol(JtwigSymbol.QUOTE)),
				string(basicParser().symbol(JtwigSymbol.DOUBLE_QUOTE))
			),
			expressionParser().push(new Constant<>(basicParser().pop())),
			action(((T)expressionParser().peek(2)).addAttribute(new T.DynamicAttribute(expressionParser().pop(1), expressionParser().pop(0)))),
			basicParser().spacing()
        );
	}
	
	protected Rule string(Rule delimiter) {
		return Sequence(
			delimiter,
			basicParser().push(""),
			OneOrMore(
				Sequence(
					TestNot(
							delimiter
					),
					ANY,
					basicParser().push(basicParser().pop() + match())
				)
            ),
            delimiter
		);
	}

}
