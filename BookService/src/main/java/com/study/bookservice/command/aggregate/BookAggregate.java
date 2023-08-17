package com.study.bookservice.command.aggregate;

import com.study.bookservice.command.commands.CreateBookCommand;
import com.study.bookservice.command.events.BookCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
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

    public BookAggregate() {
    }

    //publisher the event
    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.bookId = bookCreatedEvent.getBookId();
        this.title = bookCreatedEvent.getTitle();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.isReady();
    }
}
