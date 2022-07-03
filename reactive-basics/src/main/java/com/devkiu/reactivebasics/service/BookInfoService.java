package com.devkiu.reactivebasics.service;

import com.devkiu.reactivebasics.dto.BookInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookInfoService {
    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "Introduction to Java", "O'Riley", "KBS2447556-ER3"),
                new BookInfo(2, "Python for Dummies", "Abigail", "JKJ3FS345993"),
                new BookInfo(3, "Kotlin Basics", "Jetbrains", "K3FK6KUF93334J"),
                new BookInfo(4, "React Framework", "Facebook", "KS5JKT33JK6J5FG")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Python for Dummies", "Abigail", "JKJ3FS345993");

        return Mono.just(book);
    }
}
