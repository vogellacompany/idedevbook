package com.example.e4.rcp.todo.services.internal;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.example.e4.rcp.todo.model.CompositeTag;
import com.example.e4.rcp.todo.model.ITodoService;
import com.example.e4.rcp.todo.model.Tag;
import com.example.e4.rcp.todo.model.TagService;
import com.example.e4.rcp.todo.model.Todo;

@Component
public class TagServiceImpl implements TagService<Todo> {

	private CompositeTag<Todo> rootTag;

	public TagServiceImpl() {
		rootTag = new CompositeTag<>("root");
	}

	@Reference
	public void setITodoService(ITodoService todoService) {
		Tag<Todo> eclipseTag = new Tag<>("Eclipse");
		todoService.getTodos(eclipseTag::addAllTaggedElement);
		rootTag.addTag(eclipseTag);
	}

	@Override
	public List<Tag<Todo>> getAllTagsFromRoot() {
		return getAllTags(getRootTag());
	}

	@Override
	public List<Tag<Todo>> getAllTags(CompositeTag<Todo> parentTag) {
		List<Tag<Todo>> allTags = new ArrayList<>();

		collectAllTags(parentTag, allTags);

		return allTags;
	}

	@SuppressWarnings("unchecked")
	private void collectAllTags(CompositeTag<Todo> parentTag, List<Tag<Todo>> allTags) {
		List<Tag<Todo>> tags = parentTag.getTags();
		allTags.addAll(tags);
		tags.stream().filter(CompositeTag.class::isInstance).map(CompositeTag.class::cast)
				.forEach(cTag -> collectAllTags(cTag, allTags));
	}

	@Override
	public CompositeTag<Todo> getRootTag() {
		return rootTag;
	}

}
