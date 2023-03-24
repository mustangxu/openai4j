/**
 *
 */
package com.jayxu.openai4j.model;

import java.util.List;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class ChatResponse {
    String id;
    String object;
    Long created;
    List<Choice> choices;
    Usage usage;
}
