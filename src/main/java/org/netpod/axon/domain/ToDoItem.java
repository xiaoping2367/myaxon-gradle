package org.netpod.axon.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.netpod.axon.command.CreateToDoItemCommand;
import org.netpod.axon.command.MarkCompletedCommand;
import org.netpod.axon.event.ToDoItemCompletedEvent;
import org.netpod.axon.event.ToDoItemCreatedEvent;

public class ToDoItem extends AbstractAnnotatedAggregateRoot<String> {
	
	private static final long serialVersionUID = 1L;
	@AggregateIdentifier
	private String id;

	public ToDoItem() {
		super();
	}

	@CommandHandler
	public ToDoItem(CreateToDoItemCommand command) {
		apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
	}
	
	@CommandHandler
	public void markCompleted(MarkCompletedCommand command) {
	    apply(new ToDoItemCompletedEvent(id));
	}
	
	@EventHandler
	public void on(ToDoItemCreatedEvent event) {
		this.id = event.getTodoId();
	}
}
