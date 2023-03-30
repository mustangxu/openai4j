/**
 *
 */
package com.jayxu.openai4j.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jay Xu @2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Builder.Default
    String role = "user";
    String content;
}
