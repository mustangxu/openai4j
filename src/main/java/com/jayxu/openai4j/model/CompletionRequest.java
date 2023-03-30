/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("top_p")
    Double topP;
    Integer n;
    Boolean stream;
    @Singular("stop")
    List<String> stop;
    @SerializedName("max_tokens")
    Integer maxTokens;
    @SerializedName("presence_penalty")
    Double presencePenalty;
    @SerializedName("frequency_penalty")
    Double frequencyPenalty;
    @SerializedName("logit_bias")
    @Singular
    Map<String, Integer> logitBias;
    String user;
    String suffix;
    Integer logprobs;
    Boolean echo;
    Integer bestOf;
    @Singular("prompt")
    List<String> prompt;
}
