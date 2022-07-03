package com.devkiu.reactivebasics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private BookInfo bookInfo;
    private List<Review> reviews;
}
