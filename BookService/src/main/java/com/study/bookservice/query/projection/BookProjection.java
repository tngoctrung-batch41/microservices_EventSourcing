package com.study.bookservice.query.projection;

import com.study.bookservice.command.data.Book;
import com.study.bookservice.command.data.BookRepository;
import com.study.bookservice.command.model.BookRestModel;
import com.study.bookservice.query.queries.GetBookQuery;
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
    public List<BookRestModel> handle (GetBookQuery getBookQuery){
        List<Book> books = bookRepository.findAll();

        List<BookRestModel> bookRestModels =
            books.stream()
                    .map(book -> BookRestModel.builder()
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .isReady(book.isReady())
                            .build())
                    .collect(Collectors.toList());

        return bookRestModels;
    }
}
