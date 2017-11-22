package com.example.e4.rcp.todo.services.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.example.e4.rcp.todo.model.ITodoService;
import com.example.e4.rcp.todo.model.Tag;
import com.example.e4.rcp.todo.model.TagService;
import com.example.e4.rcp.todo.model.Todo;

@Component
public class TagServiceImpl implements TagService {

	private Tag<Tag<Todo>> rootTag;
	private ITodoService todoService;


	@Reference
	public void setITodoService(ITodoService todoService) {
		this.todoService = todoService;
		List<Todo> todos = new ArrayList<>();
		todoService.getTodos(todos::addAll);
		createRootTag(todos);
	}

	@Override
	public List<Tag<Todo>> getTags(long id) {
		List<Tag<Todo>> tags = new ArrayList<>();

		Optional<Todo> findById = findById(id);
		findById.ifPresent(todo -> findTags(todo, tags, getRootTag()));

		return tags;
	}

	private void findTags(Todo todo, List<Tag<Todo>> todosTags, Tag<?> rootTag) {
		List<?> taggedElements = rootTag.getTaggedElements();
		for (Object taggedElement : taggedElements) {
			if (taggedElement instanceof Tag) {
				findTags(todo, todosTags, (Tag<?>) taggedElement);
			} else if (todo.equals(taggedElement)) {
				@SuppressWarnings("unchecked")
				Tag<Todo> foundTag = (Tag<Todo>) rootTag;
				todosTags.add(foundTag);
			}
		}
	}

	@Override
	public Tag<Tag<Todo>> getRootTag() {
		return rootTag;
	}

	private Optional<Todo> findById(long id) {
		List<Todo> todos = new ArrayList<>();
		todoService.getTodos(todos::addAll);
		return todos.stream().filter(t -> t.getId() == id).findAny();
	}

	private void createRootTag(List<Todo> todos) {
		Tag<Todo> eclipseTag = new Tag<>("Eclipse", todos);
		List<Tag<Todo>> tagList = new ArrayList<>();
		tagList.add(eclipseTag);
		rootTag = new Tag<>("root", tagList);
	}
}
