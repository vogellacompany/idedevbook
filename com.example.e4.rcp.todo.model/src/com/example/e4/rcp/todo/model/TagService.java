package com.example.e4.rcp.todo.model;

import java.util.List;

public interface TagService {

	List<Tag<Todo>> getTags(long id);

	Tag<Tag<Todo>> getRootTag();
	
}

