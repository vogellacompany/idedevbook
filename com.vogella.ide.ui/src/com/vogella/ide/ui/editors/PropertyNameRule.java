package com.vogella.ide.ui.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class PropertyNameRule implements IRule {

	private final Token token;

	public PropertyNameRule(Token token) {
		this.token = token;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		if (c != ICharacterScanner.EOF) {
			do {
				// read until the a EOF or colon is reached
				c = scanner.read();
			} while (c != ICharacterScanner.EOF && c != ':');
			if (c == ':')
				return token;
		}
		scanner.unread();
		return Token.UNDEFINED;
	}
}
