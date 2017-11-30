package com.vogella.resources;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TodoProjectWizardPage extends WizardPage {

	public TodoProjectWizardPage() {
		super("New Todo Project");
		setTitle("New Todo Project");
		setDescription("Create a New Todo Project");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
	}

}
