/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * @author Jay Xu @2023
 */
@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class CompletionRequest {
    String model;
    @Singular
    List<Message> messages;
    Double temperature;
    @JsonProperty("top_p")
    Double topP;
    Integer n;
    Boolean stream;
    @Singular("stop")
    List<String> stop;
    @JsonProperty("max_tokens")
    Integer maxTokens;
    @JsonProperty("presence_penalty")
    Double presencePenalty;
    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;
    @JsonProperty("logit_bias")
    @Singular
    Map<String, Integer> logitBias;
    String user;
    String suffix;
    Integer logprobs;
    Boolean echo;
    Integer bestOf;
    @Singular(value = "prompt", ignoreNullCollections = true)
    List<String> prompt;
}
