/**
 *
 */
package com.jayxu.openai4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class Usage {
    @JsonProperty("prompt_tokens")
    Integer promptTokens;
    @JsonProperty("completion_tokens")
    Integer completionTokens;
    @JsonProperty("total_tokens")
    Integer totalTokens;
}
