package com.devkiu.reactivebasics.controller;

import com.devkiu.reactivebasics.dto.Book;
import com.devkiu.reactivebasics.dto.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class ApiClientController {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();

    @GetMapping
    public Flux<Book> getAllBooks() {
        Flux<Book> bookFlux = webClient.get().uri("/books")
                .retrieve().bodyToFlux(Book.class);
        return bookFlux.map(book -> {
            List<Review> reviews = book.getReviews().stream().peek(review -> {
                double rating = review.getRating() == 0 ? 1 : review.getRating();
                review.setRating(rating);
            }).collect(Collectors.toList());

            book.setReviews(reviews);
            return book;
        });
    }
}
