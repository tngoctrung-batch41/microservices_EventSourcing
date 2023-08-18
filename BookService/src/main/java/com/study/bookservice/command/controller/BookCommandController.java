package com.study.bookservice.command.controller;

import com.study.bookservice.command.commands.CreateBookCommand;
import com.study.bookservice.command.commands.DeleteBookCommand;
import com.study.bookservice.command.commands.UpdateBookCommand;
import com.study.bookservice.command.model.BookRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public String updateBook (@RequestBody BookRestModel bookRestModel, @PathVariable String id){
        UpdateBookCommand updateBookCommand =
            UpdateBookCommand.builder()
                    .bookId(id)
                    .author(bookRestModel.getAuthor())
                    .title(bookRestModel.getTitle())
                    .isReady(bookRestModel.isReady())
                    .build();
    String result = commandGateway.sendAndWait(updateBookCommand);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook (@PathVariable String id){
        DeleteBookCommand deleteBookCommand =
                DeleteBookCommand.builder()
                        .bookId(id)
                        .build();

        String result = commandGateway.sendAndWait(deleteBookCommand);
        return result;
    }

}
