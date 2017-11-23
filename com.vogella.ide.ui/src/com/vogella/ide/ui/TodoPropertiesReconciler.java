package com.vogella.ide.ui;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class TodoPropertiesReconciler extends PresentationReconciler implements IPresentationReconciler {

	private final TextAttribute tagAttribute = new TextAttribute(
			Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));

	public TodoPropertiesReconciler() {
		RuleBasedScanner scanner = new RuleBasedScanner();
		IRule rule = new SingleLineRule(" ", ":", new Token(tagAttribute));
		scanner.setRules(new IRule[] { rule });
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
		this.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		this.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
	}

}
