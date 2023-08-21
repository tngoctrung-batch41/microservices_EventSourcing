package com.study.bookservice.command.controller;

import com.study.bookservice.command.commands.CreateBookCommand;
import com.study.bookservice.command.commands.DeleteAllBookCommand;
import com.study.bookservice.command.commands.DeleteBookCommand;
import com.study.bookservice.command.commands.UpdateBookCommand;
import com.study.bookservice.command.model.BookRequestModel;
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
    public String addProduct (@RequestBody BookRequestModel bookRequestModel){
        CreateBookCommand createProductCommand =
                CreateBookCommand.builder()
                        .bookId(UUID.randomUUID().toString())
                        .title(bookRequestModel.getTitle())
                        .author(bookRequestModel.getAuthor())
                        .isReady(true)
                        .build();
//        send this command to command gateway
        String result = commandGateway.sendAndWait(createProductCommand);
        return "Created book with id: "+result;
    }

    @PutMapping("/update/{id}")
    public String updateBook (@RequestBody BookRequestModel bookRequestModel, @PathVariable String id){
        UpdateBookCommand updateBookCommand =
            UpdateBookCommand.builder()
                    .bookId(id)
                    .author(bookRequestModel.getAuthor())
                    .title(bookRequestModel.getTitle())
                    .isReady(bookRequestModel.isReady())
                    .build();
         commandGateway.sendAndWait(updateBookCommand);
        return "Updated book";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook (@PathVariable String id){
        DeleteBookCommand deleteBookCommand =
                DeleteBookCommand.builder()
                        .bookId(id)
                        .build();
         commandGateway.sendAndWait(deleteBookCommand);
        return "Deleted book";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        DeleteAllBookCommand deleteAllBookCommand = DeleteAllBookCommand.builder().build();
        commandGateway.sendAndWait(deleteAllBookCommand);
        return "Deleted ALl Book";
    }



}
