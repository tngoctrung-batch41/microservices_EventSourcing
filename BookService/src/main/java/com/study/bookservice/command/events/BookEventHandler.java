package com.study.bookservice.command.events;

import com.study.bookservice.command.data.Book;
import com.study.bookservice.command.data.BookRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@RequiredArgsConstructor
@ProcessingGroup("book")
public class BookEventHandler {

    private final BookRepository bookRepository;
    @EventHandler
    public void on (BookCreatedEvent event) throws Exception {
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
//        throw new Exception("Exception Occurred");
    }

    @EventHandler
    public void on (BookUpdatedEvent event) throws Exception {
        Book book = bookRepository.findById(event.getBookId()).orElseThrow(()-> new NotFoundException("Not Found This Book"));
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
//        throw new Exception("Exception Occurred");
    }

    @EventHandler
    public void on (BookDeletedEvent event) throws Exception {
        Book book = bookRepository.findById(event.getBookId()).orElseThrow(()-> new NotFoundException("Not Found This Book"));
        bookRepository.deleteById(book.getBookId());
//        throw new Exception("Exception Occurred");
    }

    @EventHandler
    public void on (DeletedAllBookEvent event) throws Exception {
        bookRepository.deleteAll();
//        throw new Exception("Exception Occurred");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
