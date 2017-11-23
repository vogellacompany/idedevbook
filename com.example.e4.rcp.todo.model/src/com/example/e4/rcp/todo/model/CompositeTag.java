package com.example.e4.rcp.todo.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeTag<T> extends Tag<Tag<T>> {

	private List<Tag<T>> childTags;

	public CompositeTag(String label) {
		super(label);
		childTags = new ArrayList<>();
	}

	public List<Tag<T>> getTags() {
		return childTags;
	}

	public void addTag(Tag<T> tag) {
		childTags.add(tag);
	}

	public void removeTag(Tag<T> element) {
		childTags.remove(element);
	}
}
