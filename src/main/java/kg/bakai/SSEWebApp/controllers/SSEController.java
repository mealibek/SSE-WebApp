package kg.bakai.SSEWebApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import java.time.Duration;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/server-events")
public class SSEController {

    @GetMapping(produces = org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE, path = "/hello")
    public Flux<ServerSentEvent<String>> getEvents() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .data("Hello User " + (sequence + 1))
                        .build());
    }
}
