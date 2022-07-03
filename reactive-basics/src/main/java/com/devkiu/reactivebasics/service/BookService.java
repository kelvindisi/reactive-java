package com.devkiu.reactivebasics.service;

import com.devkiu.reactivebasics.dto.Book;
import com.devkiu.reactivebasics.dto.Review;
import com.devkiu.reactivebasics.exception.BookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;


    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();

        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();

                    return reviews.map(review -> new Book(bookInfo, review))
                            .delayElement(Duration.ofMillis(new Random().nextInt(3000)));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is:" + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .log();
    }
    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();

        return allBooks.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();

                    return reviews.map(review -> new Book(bookInfo, review))
                            .delayElement(Duration.ofMillis(new Random().nextInt(3000)));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is:" + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(2)
                .log();
    }
    public Mono<Book> getBookById(long bookId) {
        var book = bookInfoService.getBookById(bookId);
        var reviews = reviewService.getReviews(bookId).collectList();

        return book.zipWith(reviews, Book::new).log();
    }
}
