package com.vogella.ide.outline;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public class TodoContentOutlinePage extends ContentOutlinePage {

	private IDocument doc;

	public TodoContentOutlinePage(IDocument doc) {
		this.doc = doc;
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new TodoOutlineContentProvider());
		viewer.setInput(doc);
	}
}
