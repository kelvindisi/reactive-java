package com.devkiu.reactivebasics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    private long reviewId;
    private long bookId;
    private double rating;
    private String comments;
}
