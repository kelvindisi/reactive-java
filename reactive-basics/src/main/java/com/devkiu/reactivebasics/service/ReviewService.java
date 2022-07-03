package com.devkiu.reactivebasics.service;

import com.devkiu.reactivebasics.dto.Review;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Random;

@Service
public class ReviewService {
    public Flux<Review> getReviews(long bookId) {
        Random random = new Random();

        return Flux.range(1, random.nextInt(10))
                 .flatMap(integer -> {
                     int rating = random.nextInt(5);
                     String comment = rating > 3? "Good": "Not a good movie";
                     return Flux.just(new Review(
                             Integer.parseInt(bookId + "" + integer),
                             bookId,
                             rating,
                             comment
                     ));
                 });
    }
}
