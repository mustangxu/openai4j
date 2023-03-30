/**
 *
 */
package com.jayxu.openai4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.GsonBuilder;
import com.jayxu.openai4j.model.CompletionRequest;
import com.jayxu.openai4j.model.CompletionResponse;
import com.jayxu.openai4j.model.ErrorResponse;
import com.jayxu.openai4j.model.ServiceException;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * @author Jay Xu @2023
 */
@Builder
public class StreamOpenAiService {
    private static final String EOF = "[DONE]";
    private static final ParameterizedTypeReference<ServerSentEvent<String>> SSE_TYPE = new ParameterizedTypeReference<>() {
    };
    private transient WebClient client;
    @NonNull
    private String apikey;
    @Setter
    private boolean debug;

    public Flux<CompletionResponse> createCompletions(CompletionRequest chat) {
        return this.doRequest("completions", chat, CompletionResponse.class);
    }

    public Flux<CompletionResponse> createChat(CompletionRequest chat) {
        return this.doRequest("chat/completions", chat,
            CompletionResponse.class);
    }

    private <T> Flux<T> doRequest(String path, Object body,
            Class<T> responseType) {
        var gson = new GsonBuilder().setLenient().create();

        return this.client.post().uri(path)
            .contentType(MediaType.APPLICATION_JSON).bodyValue(body).retrieve()
            .bodyToFlux(SSE_TYPE).mapNotNull(ServerSentEvent::data)
            .filter(e -> !EOF.equals(e))
            .mapNotNull(e -> gson.fromJson(e, responseType));
    }

    public StreamOpenAiService init() {
        var c = WebClient.builder().baseUrl(OpenAiService.BASE_URL)
            .defaultHeader("Authorization", "Bearer " + this.apikey)
            .filter(ExchangeFilterFunction.ofResponseProcessor(resp -> {
                if (!resp.statusCode().isError()) {
                    return Mono.just(resp);
                }

                return resp.bodyToMono(ErrorResponse.class).flatMap(e -> Mono
                    .error(() -> new ServiceException(resp.statusCode().value(),
                        e.getError())));
            }));

        var httpClient = HttpClient.create().proxyWithSystemProperties();
        if (this.debug) {
            httpClient = httpClient
                .wiretap(StreamOpenAiService.class.getName());
        }

        this.client = c
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

        return this;
    }
}
