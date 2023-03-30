/**
 *
 */
package com.jayxu.openai4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
@Builder
public class ImageRequest {
    String prompt;
    @Builder.Default
    Integer n = 1;
    @Builder.Default
    String size = "1024x1024";
    /**
     * <code>url</code> or <code>b64_json</code>
     */
    @JsonProperty("response_format")
    @Builder.Default
    String responseFormat = "url";
    String user;
}
