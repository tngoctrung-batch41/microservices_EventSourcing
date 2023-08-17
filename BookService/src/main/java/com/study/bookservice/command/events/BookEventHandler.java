package com.study.bookservice.command.events;

import com.study.bookservice.command.data.Book;
import com.study.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventHandler {

    private final BookRepository bookRepository;
    @EventHandler
    public void on (BookCreatedEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }
}
