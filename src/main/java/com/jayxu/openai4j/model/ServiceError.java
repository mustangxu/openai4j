/**
 *
 */
package com.jayxu.openai4j.model;

import lombok.Data;

@Data
public class ServiceError {
    String message;
    String type;
    String param;
    String code;
}
