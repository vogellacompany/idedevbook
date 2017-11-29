package com.vogella.ide.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.vogella.swt.widgets.Checkbox;
import com.vogella.swt.widgets.ImageWithText;

public class PlaygroundPart {

	@PostConstruct
	public void createPartControl(Composite parent) {
		Checkbox checkbox = new Checkbox(parent, SWT.NONE);
		checkbox.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			System.out.println("Checked Widget");
		}));
		
		new ImageWithText(parent, SWT.NONE);
	}
}
