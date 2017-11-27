package com.vogella.ide.navigator.simple.view;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.IDescriptionProvider;

import com.vogella.ide.navigator.simple.model.Child;
import com.vogella.ide.navigator.simple.model.Parent;

public class CNFLabelProvider extends LabelProvider implements ILabelProvider, IDescriptionProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Parent) {
			return ((Child) element).getName() + " [ " + ((Parent) element).getChildren().length + " ]";
		} else if (element instanceof Child) {
			return ((Child) element).getName();
		}
		return null;
	}

	@Override
	public String getDescription(Object element) {
		String text = getText(element);
		return "This is a description of " + text;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Parent) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if (element instanceof Child) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		}
		return null;
	}
}