package com.study.bookservice.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;
}
