/**
 *
 */
package com.jayxu.openai4j.model;

import lombok.Data;

@Data
public class Error {
    String message;
    String type;
    String param;
    String code;
}
