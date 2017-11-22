package com.vogella.ide.navigator.simple.actions;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class MyActionProvider extends CommonActionProvider {
	private MyAction doubleClickAction;

	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		doubleClickAction = new MyAction();

		aSite.getStructuredViewer().addSelectionChangedListener(doubleClickAction);

	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		// forward doubleClick to doubleClickAction
		actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, doubleClickAction);
	}
}
