/**
 *
 */
package com.jayxu.openai4j.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
@Builder
public class Message {
    String role;
    String content;
}
