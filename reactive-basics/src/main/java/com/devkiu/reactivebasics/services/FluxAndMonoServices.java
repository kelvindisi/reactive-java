package com.devkiu.reactivebasics.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

//defaultIfEmpty -> "Is used to return data in-case Flux doesn't contain data"
//switchIfEmpty -> "Used to return different data in Flux has no data
//              -> "unlike defaultIfEmpty it allows data manipulation like any other Flux of Mono
public class FluxAndMonoServices {
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int length) {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .filter(s -> s.length() >= length)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int length) {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .filter(s -> s.length() >= length)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitFluxAsync() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .flatMap(s -> Flux.just(s).delayElements(
                        Duration.ofMillis(
                                new Random().nextInt(1000)
                        )
                )).log();
    }

    public Mono<String> fruitsMono() {
        return Mono.just("Spring Boot")
                .log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Spring Boot")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitFluxConcatMap() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .concatMap(s -> Flux.just(s.split("")).delayElements(
                        Duration.ofMillis(new Random().nextInt(1000))
                )).log();
    }

    public Flux<String> fruitFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .transform(filterData)
                .log();
    }
    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Java")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitFluxMerge() {
        Flux<String> web = Flux.just("Spring", "Spring Boot")
                .delayElements(Duration.ofMillis(50));
        Flux<String> language = Flux.just("Java", "Kotlin")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(web, language).log();
    }
    public Flux<String> fruitFluxMergeWith() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");

        return web.mergeWith(language).log();
    }

    public Flux<String> fruitFluxMergeSequential() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");

        return Flux.mergeSequential(web, language).log();
    }

    public Flux<String> fruitsConcat() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");

        return Flux.concat(web, language).log();
    }
    public Flux<String> fruitsConcatWith() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");

        return web.concatWith(language);
    }

    public Flux<String> fruitZip() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");

        return Flux.zip(web, language, (first, second) -> first + second).log();
    }

    public Flux<String> fruitFluxZipTuple() {
        Flux<String> web = Flux.just("Spring", "Spring Boot");
        Flux<String> language = Flux.just("Java", "Kotlin");
        Flux<String> categories = Flux.just("Micro-Service", "API");

        return Flux.zip(web, language, categories)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3())
                .log();
    }

//    Some doOn... methods -> subscribe, next, error...
    public Flux<String> fruitsFluxFilterDoOn(int length) {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .filter(s -> s.length() >= length)
                .doOnNext(System.out::println)
                .doOnSubscribe((subscription)-> System.out.println("subscription.toString() = " + subscription))
                .doOnComplete(()-> System.out.println("Completed"))
                .log();
    }

//    Error handling in Reactive Code
    public Flux<String> fruitFluxOnErrorReturn() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .concatWith(Flux.error(
                        new RuntimeException("Exception occured")
                ))
                .onErrorReturn("Android")
                .log();
    }

    public Flux<String> fruitFluxOnErrorContinue() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .map(s -> {
                    if (s.length() > 5)
                        return s;
                    else
                        throw new RuntimeException(String.format("%s has length of less than 6 characters", s));
                })
                .onErrorContinue((throwable, obj)-> System.out.println(throwable.getMessage()))
                .log();
    }
    public Flux<String> fruitFluxOnErrorMap() {
        return Flux.fromIterable(List.of("Spring", "Spring Boot", "Java", "Kotlin"))
                .map(s -> {
                    if (s.length() > 5)
                        return s;
                    else
                        throw new RuntimeException(String.format("%s has length of less than 6 characters", s));
                })
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From onError Map");
                })
                .log();
    }

}
