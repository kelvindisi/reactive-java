package com.devkiu.reactivebasics.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoServicesTest {
    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        Flux<String> fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Spring", "Spring Boot", "Java", "Kotlin")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        Mono<String> monoBest = fluxAndMonoServices.fruitsMono();

        StepVerifier.create(monoBest)
                .expectNext("Spring Boot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        Flux<String> fruitsVerifier = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(fruitsVerifier)
                .expectNext("SPRING", "SPRING BOOT", "JAVA", "KOTLIN")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        Flux<String> fruitsWithMinLength = fluxAndMonoServices.fruitsFluxFilter(6);

        StepVerifier.create(fruitsWithMinLength)
                .expectNext("Spring", "Spring Boot", "Kotlin")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        Flux<String> fluxFilterMap = fluxAndMonoServices.fruitsFluxFilterMap(6);

        StepVerifier.create(fluxFilterMap)
                .expectNext("SPRING", "SPRING BOOT", "KOTLIN")
                .verifyComplete();
    }

    @Test
    void fruitFluxAsync() {
        Flux<String> fluxAsync = fluxAndMonoServices.fruitFluxAsync();

        StepVerifier.create(fluxAsync)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        Mono<List<String>> listMono = fluxAndMonoServices.fruitMonoFlatMap();

        StepVerifier.create(listMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitFluxConcatMap() {
        Flux<String> concatMapFlux = fluxAndMonoServices.fruitFluxConcatMap();

        StepVerifier.create(concatMapFlux)
                .expectNextCount(27)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        Flux<String> monoFlatMapMany = fluxAndMonoServices.fruitMonoFlatMapMany();

        StepVerifier.create(monoFlatMapMany)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitFluxTransform() {
        Flux<String> fluxTransform = fluxAndMonoServices.fruitFluxTransform(5);

        StepVerifier.create(fluxTransform)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void fruitsConcat() {
        Flux<String> fruitsConcat = fluxAndMonoServices.fruitsConcat();

        StepVerifier.create(fruitsConcat)
                .expectNext("Spring", "Spring Boot", "Java", "Kotlin")
                .verifyComplete();
    }

    @Test
    void fruitsConcatWith() {
        Flux<String> fruitsConcat = fluxAndMonoServices.fruitsConcat();

        StepVerifier.create(fruitsConcat)
                .expectNext("Spring", "Spring Boot", "Java", "Kotlin")
                .verifyComplete();
    }

    @Test
    void fruitFluxMerge() {
        Flux<String> fruitsMerge = fluxAndMonoServices.fruitFluxMerge();

        StepVerifier.create(fruitsMerge)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitFluxMergeWith() {
        Flux<String> fluxMergeWith = fluxAndMonoServices.fruitFluxMergeWith();

        StepVerifier.create(fluxMergeWith)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitFluxMergeSequential() {
        Flux<String> mergeSequential = fluxAndMonoServices.fruitFluxMergeSequential();

        StepVerifier.create(mergeSequential)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fruitZip() {
        Flux<String> stringFlux = fluxAndMonoServices.fruitZip();

        StepVerifier.create(stringFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitFluxZipTuple() {
        Flux<String> stringFlux = fluxAndMonoServices.fruitFluxZipTuple();

        StepVerifier.create(stringFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        Flux<String> stringFlux = fluxAndMonoServices.fruitsFluxFilterDoOn(5);

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void fruitFluxOnErrorReturn() {
        Flux<String> onErrorReturn = fluxAndMonoServices.fruitFluxOnErrorReturn();

        StepVerifier.create(onErrorReturn)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitFluxOnErrorContinue() {
        Flux<String> onErrorReturn = fluxAndMonoServices.fruitFluxOnErrorContinue();

        StepVerifier.create(onErrorReturn)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void fruitFluxOnErrorMap() {
        Flux<String> onErrorReturn = fluxAndMonoServices.fruitFluxOnErrorMap();

        StepVerifier.create(onErrorReturn)
                .expectNextCount(2)
                .expectError(IllegalStateException.class)
                .verify();
    }
}