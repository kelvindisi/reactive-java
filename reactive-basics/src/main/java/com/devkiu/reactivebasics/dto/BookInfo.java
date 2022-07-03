package com.devkiu.reactivebasics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInfo {
    private long bookId;
    private String title;
    private String author;
    private String ISBN;
}
