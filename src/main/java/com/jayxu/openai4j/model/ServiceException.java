/**
 *
 */
package com.jayxu.openai4j.model;

import java.io.Serial;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Jay Xu @2023
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5033966390062962803L;
    int status;
    com.jayxu.openai4j.model.ErrorResponse.Error error;
}
