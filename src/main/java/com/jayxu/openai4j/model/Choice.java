/**
 *
 */
package com.jayxu.openai4j.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class Choice {
    Integer index;
    Message message;
    @SerializedName("finish_reason")
    String finishReason;
    Integer logprobs;
    String text;
}
