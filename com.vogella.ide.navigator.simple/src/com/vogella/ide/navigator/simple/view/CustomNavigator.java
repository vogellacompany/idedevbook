package com.vogella.ide.navigator.simple.view;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.navigator.CommonNavigator;

import com.vogella.ide.navigator.simple.model.FakeInitialInput;

public class CustomNavigator extends CommonNavigator {

	protected IAdaptable getInitialInput() {
		return new FakeInitialInput();
	}

}
