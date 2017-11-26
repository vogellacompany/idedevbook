package com.vogella.ide.outline;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class TodoOutlineAdapterFactory implements IAdapterFactory {

	public static final Class<?>[] adapterClasses = new Class<?>[] { IContentOutlinePage.class };

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {

		if (adaptableObject instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) adaptableObject;
			IDocumentProvider documentProvider = textEditor.getDocumentProvider();
			return adapterType.cast(new TodoContentOutlinePage(documentProvider.getDocument(textEditor.getEditorInput())));
		}

		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return adapterClasses;
	}

}
