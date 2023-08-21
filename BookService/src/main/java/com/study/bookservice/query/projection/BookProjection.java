package com.study.bookservice.query.projection;

import com.study.bookservice.command.data.Book;
import com.study.bookservice.command.data.BookRepository;
import com.study.bookservice.command.model.BookRequestModel;
import com.study.bookservice.query.model.BookResponseModel;
import com.study.bookservice.query.queries.GetAllBookQuery;
import com.study.bookservice.query.queries.GetBookQuery;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookProjection {
    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle (GetAllBookQuery getAllBookQuery){
        List<Book> books = bookRepository.findAll();
        List<BookResponseModel> bookResponseModels =
            books.stream()
                    .map(book -> BookResponseModel.builder()
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .isReady(book.isReady())
                            .build())
                    .collect(Collectors.toList());

        return bookResponseModels;
    }

    @QueryHandler
    public BookResponseModel handle(GetBookQuery getBookQuery){
        Book book = bookRepository.findById(getBookQuery.getBookId()).orElseThrow(()-> new NotFoundException("Not Found"));
        BookResponseModel model = BookResponseModel.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isReady(book.isReady())
                .build();
        return model;
    }
}
