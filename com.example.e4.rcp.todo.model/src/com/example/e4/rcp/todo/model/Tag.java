package com.example.e4.rcp.todo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tag<T> {

	public static String LABEL_FIELD = "label";

	public static String TAGGEDELEMENTS_FIELD = "taggedElements";

	private String label;

	private List<T> taggedElements;

	public Tag(String label) {
		this.label = label;
		taggedElements = new ArrayList<>();
	}

	public String getLabel() {
		return label;
	}

	public List<T> getTaggedElements() {
		return taggedElements;
	}

	public void addTaggedElement(T element) {
		taggedElements.add(element);
	}

	public void addAllTaggedElement(Collection<T> element) {
		taggedElements.addAll(element);
	}

	public void removeTaggedElement(T element) {
		taggedElements.remove(element);
	}

	public void removeAllTaggedElement(Collection<T> element) {
		taggedElements.removeAll(element);
	}
	
	@Override
	public String toString() {
		return "[Tag] " + getLabel();
	}
}
