package com.vogella.ide.navigator.simple.view;

import org.eclipse.jface.viewers.ITreeContentProvider;

import com.vogella.ide.navigator.simple.model.Child;
import com.vogella.ide.navigator.simple.model.FakeInitialInput;
import com.vogella.ide.navigator.simple.model.Parent;

public class CNFContentProvider implements ITreeContentProvider {

	private static final Object[] EMPTY_ARRAY = new Object[0];
	private Parent[] parents;

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof FakeInitialInput) {
			if (parents == null) {
				initializeParents(parentElement);
			}
			return parents;
		} else if (parentElement instanceof Parent) {
			return ((Parent) parentElement).getChildren();
		} else if (parentElement instanceof Child) {
			return EMPTY_ARRAY;
		} else {
			return EMPTY_ARRAY;
		}
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Child) {
			return ((Child) element).getParent();
		} else if (element instanceof Parent) {
			return ((Parent) element).getRootElement();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof FakeInitialInput || element instanceof Parent);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public void dispose() {
		this.parents = null;
	}

	private void initializeParents(Object parentElement) {
		this.parents = new Parent[3];
		for (int i = 0; i < this.parents.length; i++) {
			this.parents[i] = new Parent("Parent " + i);
			this.parents[i].setRootElement(parentElement);
			Child[] children = new Child[3];
			for (int j = 0; j < children.length; j++) {
				children[j] = new Child("Child " + i + j);
			}
			this.parents[i].setChildren(children);
		}
	}
}