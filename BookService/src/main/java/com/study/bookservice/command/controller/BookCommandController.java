package com.study.bookservice.command.controller;

import com.study.bookservice.command.commands.CreateBookCommand;
import com.study.bookservice.command.model.BookRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookCommandController {
    private final CommandGateway commandGateway;

    public BookCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public String addProduct (@RequestBody BookRestModel bookRestModel){
        CreateBookCommand createProductCommand =
                CreateBookCommand.builder()
                        .bookId(UUID.randomUUID().toString())
                        .title(bookRestModel.getTitle())
                        .author(bookRestModel.getAuthor())
                        .isReady(true)
                        .build();
//        send this command to command gateway
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }
}
