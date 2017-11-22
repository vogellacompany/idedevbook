package com.vogella.ide.navigator.simple.model;

import java.util.Arrays;

public class Parent extends Child {
	private Child[] children = new Child[0];
	private Object rootElement;

	public Parent(String name) {
		super(name);
	}

	public void setChildren(Child[] children) {
		if (children != null) {
			setChildrensParent(null, this.children);
		}
		this.children = children;
		setChildrensParent(this, this.children);
	}

	/**
	 * Sets children's parent
	 * 
	 * @param parent
	 *            parent to be set
	 * @param children
	 *            children to set the parent
	 */
	private static void setChildrensParent(Parent parent, Child[] children) {
		for (int i = 0; i < children.length; i++) {
			children[i].setParent(parent);
		}
	}

	public Object getRootElement() {
		return rootElement;
	}

	public void setRootElement(Object rootElement) {
		this.rootElement = rootElement;
	}

	public Child[] getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "Parent [children=" + Arrays.toString(children) + ", rootElement=" + rootElement + "]";
	}
	
	


}