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
public class ChatRequest {
    String model;
    List<Message> messages;
    @Builder.Default
    double temperature = 1;
    @SerializedName("top_p")
    @Builder.Default
    double topP = 1;
    @Builder.Default
    int n = 1;
    boolean stream;
    List<String> stop;
    @SerializedName("max_tokens")
    Integer maxTokens;
    @SerializedName("presence_penalty")
    double presencePenalty;
    @SerializedName("frequency_penalty")
    double frequencyPenalty;
    @SerializedName("logit_bias")
    Map<String, Integer> logitBias;
    String user;
}
