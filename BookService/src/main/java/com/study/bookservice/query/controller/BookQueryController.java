package com.study.bookservice.query.controller;


import com.study.bookservice.command.model.BookRestModel;
import com.study.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookQueryController {

    private final QueryGateway queryGateway;

    public BookQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<BookRestModel> gettAll(){
        GetBookQuery getBookQuery = new GetBookQuery();

        List<BookRestModel> bookRestModelList =
        queryGateway.query(getBookQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)).join();

        return bookRestModelList;
    }
}
