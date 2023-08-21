package com.study.bookservice.query.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseModel {
    private String title;
    private String author;
    private boolean isReady;
}
