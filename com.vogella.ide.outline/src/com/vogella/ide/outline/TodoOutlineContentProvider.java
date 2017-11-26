package com.vogella.ide.outline;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoOutlineContentProvider implements ITreeContentProvider {

	private static final Logger logger = LoggerFactory.getLogger(TodoOutlineContentProvider.class);

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IDocument) {
			IDocument doc = (IDocument) inputElement;
			int numberOfLines = doc.getNumberOfLines();
			List<String> elements = new ArrayList<>(numberOfLines);
			for (int i = 0; i < numberOfLines; i++) {
				try {
					IRegion lineInformation = doc.getLineInformation(i);
					String string = doc.get(lineInformation.getOffset(), lineInformation.getLength());
					elements.add(string);
				} catch (BadLocationException e) {
					logger.error(e.getMessage(), e);
				}
			}
			return elements.toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

}
