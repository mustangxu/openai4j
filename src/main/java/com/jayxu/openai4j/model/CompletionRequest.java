/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
@Builder
public class CompletionRequest {
    String model;
    List<Message> messages;
    Double temperature;
    @SerializedName("top_p")
    Double topP;
    Integer n;
    Boolean stream;
    List<String> stop;
    @SerializedName("max_tokens")
    Integer maxTokens;
    @SerializedName("presence_penalty")
    Double presencePenalty;
    @SerializedName("frequency_penalty")
    Double frequencyPenalty;
    @SerializedName("logit_bias")
    Map<String, Integer> logitBias;
    String user;
    String suffix;
    Integer logprobs;
    Boolean echo;
    Integer bestOf;
    List<String> prompt;
}
