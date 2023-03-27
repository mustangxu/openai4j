/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jay Xu @2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CompletionResponse extends BaseResponse {
    String id;
    String object;
    List<Choice> choices;
    Usage usage;
}
