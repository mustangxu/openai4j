/**
 *
 */
package com.jayxu.openai4j.model;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class ServiceError implements Serializable {
    @Serial
    private static final long serialVersionUID = 3275226795589819416L;
    String message;
    String type;
    String param;
    String code;
}
