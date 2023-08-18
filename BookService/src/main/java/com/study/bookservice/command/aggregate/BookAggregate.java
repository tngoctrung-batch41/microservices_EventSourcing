package com.study.bookservice.command.aggregate;

import com.study.bookservice.command.commands.CreateBookCommand;
import com.study.bookservice.command.commands.DeleteBookCommand;
import com.study.bookservice.command.commands.UpdateBookCommand;
import com.study.bookservice.command.events.BookCreatedEvent;
import com.study.bookservice.command.events.BookDeletedEvent;
import com.study.bookservice.command.events.BookUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand createProductCommand) {
        //you can perform all the validations
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        //coppy all the properties of crea to created
        BeanUtils.copyProperties(createProductCommand,bookCreatedEvent);
        //pubplish created event
        AggregateLifecycle.apply(bookCreatedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.bookId = bookCreatedEvent.getBookId();
        this.title = bookCreatedEvent.getTitle();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.isReady();
    }

    @CommandHandler
    public void handle (UpdateBookCommand updateBookCommand) {
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand,bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(BookUpdatedEvent bookCreatedEvent){
        this.bookId = bookCreatedEvent.getBookId();
        this.title = bookCreatedEvent.getTitle();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.isReady();
    }

    @CommandHandler
    public void handle (DeleteBookCommand deleteBookCommand) {
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(DeleteBookCommand deleteBookEvent){
        this.bookId = deleteBookEvent.getBookId();
    }
    public BookAggregate() {
    }


}
