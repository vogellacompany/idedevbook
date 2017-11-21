package com.example.e4.rcp.todo.services.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.example.e4.rcp.todo.events.MyEventConstants;
import com.example.e4.rcp.todo.model.ITodoService;
import com.example.e4.rcp.todo.model.Todo;

@Component
public class TodoServiceImpl implements ITodoService {

	private static AtomicInteger current = new AtomicInteger(1);
	private List<Todo> todos;
	private IEventBroker eventBroker;

	public TodoServiceImpl() {
		todos = createInitialModel();
	}

	@Reference
	public void bindEventBroker(IEventBroker eventBroker) {
		this.eventBroker = eventBroker;
	}

	@Override
	public void getTodos(Consumer<List<Todo>> todosConsumer) {
		// always pass a new copy of the data
		todosConsumer.accept(todos.stream().map(Todo::copy).collect(Collectors.toList()));
	}

	protected List<Todo> getTodosInternal() {
		return todos;
	}

	// create or update an existing instance
	@Override
	public synchronized boolean saveTodo(Todo newTodo) {
		Optional<Todo> todoOptional = findById(newTodo.getId());

		// get the actual task or create a new one
		Todo todo = todoOptional.orElse(new Todo(current.getAndIncrement()));
		todo.setSummary(newTodo.getSummary());
		todo.setDescription(newTodo.getDescription());
		todo.setDone(newTodo.isDone());
		todo.setDueDate(newTodo.getDueDate());

		// send out events
		if (todoOptional.isPresent()) {
			eventBroker.post(MyEventConstants.TOPIC_TODO_UPDATE,
					createEventData(MyEventConstants.TOPIC_TODO_UPDATE, String.valueOf(todo.getId())));
		} else {
			todos.add(todo);
			eventBroker.post(MyEventConstants.TOPIC_TODO_NEW,
					createEventData(MyEventConstants.TOPIC_TODO_NEW, String.valueOf(todo.getId())));
		}
		return true;
	}

	@Override
	public Optional<Todo> getTodo(long id) {
		return findById(id).map(Todo::copy);
	}

	@Override
	public boolean deleteTodo(long id) {
		Optional<Todo> deleteTodo = findById(id);

		deleteTodo.ifPresent(todo -> {
			todos.remove(todo);

			// configure the event
			eventBroker.post(MyEventConstants.TOPIC_TODO_DELETE,
					createEventData(MyEventConstants.TOPIC_TODO_DELETE, String.valueOf(todo.getId())));
		});

		return deleteTodo.isPresent();
	}

	// create example data
	private List<Todo> createInitialModel() {
		List<Todo> list = new ArrayList<>();
		list.add(createTodo("Application model", "Flexible and extensible"));
		list.add(createTodo("DI", "@Inject as programming mode"));
		list.add(createTodo("OSGi", "Services"));
		list.add(createTodo("SWT", "Using widgets"));
		list.add(createTodo("JFace", "Especially Viewers!"));
		list.add(createTodo("Eclipse services", "Selection, model, Part"));
		list.add(createTodo("CSS Styling", "Style your application"));
		list.add(createTodo("Renderer", "Different UI toolkit"));
		list.add(createTodo("Compatibility Layer", "Run Eclipse 3.x"));
		return list;
	}

	private Todo createTodo(String summary, String description) {
		return new Todo(current.getAndIncrement(), summary, description, false, new Date());
	}

	private Optional<Todo> findById(long id) {
		return getTodosInternal().stream().filter(t -> t.getId() == id).findAny();
	}

	private Map<String, String> createEventData(String topic, String todoId) {
		Map<String, String> map = new HashMap<>();
		// in case the receiver wants to check the topic
		map.put(MyEventConstants.TOPIC_TODO, topic);
		// which task has changed
		map.put(Todo.FIELD_ID, todoId);
		return map;
	}
}
