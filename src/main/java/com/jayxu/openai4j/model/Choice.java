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
public class Choice {
    Integer index;
    Message message;
    Message delta;
    @JsonProperty("finish_reason")
    String finishReason;
    Integer logprobs;
    String text;
}
