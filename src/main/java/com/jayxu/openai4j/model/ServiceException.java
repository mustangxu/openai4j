/**
 *
 */
package com.jayxu.openai4j.model;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jay Xu @2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5033966390062962803L;
    int status;
    com.jayxu.openai4j.model.Error error;

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
