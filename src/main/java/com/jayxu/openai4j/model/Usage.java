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
public class Usage {
    @SerializedName("prompt_tokens")
    Integer promptTokens;
    @SerializedName("completion_tokens")
    Integer completionTokens;
    @SerializedName("total_tokens")
    Integer totalTokens;
}
