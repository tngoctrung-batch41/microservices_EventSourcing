package com.study.bookservice.command.model;

import lombok.*;

@Data
@Builder
public class BookRestModel {
    private String title;
    private String author;
    private boolean isReady;
}
