/**
 *
 */
package com.jayxu.openai4j.model;

import lombok.Data;

/**
 * @author Jay Xu @2023
 */
@Data
public class ErrorResponse {
    Error error;

    @Data
    public static class Error {
        String message;
        String type;
        String param;
        String code;
    }
}
