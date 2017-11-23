package com.example.e4.rcp.todo.model;

import java.util.List;

public interface TagService<T> {
	
	List<Tag<T>> getAllTagsFromRoot();
	
	List<Tag<T>> getAllTags(CompositeTag<T> rootTag);

	CompositeTag<T> getRootTag();
}
