package com.vogella.ide.navigator.simple.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class MyAction extends Action  implements ISelectionChangedListener{

	@Override
	public void run() {
		System.out.println("Hello");
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection structuredSelection = event.getStructuredSelection();
		if (structuredSelection.getFirstElement() != null) {
			System.out.println(structuredSelection.getFirstElement());
		}
		
	}
}
