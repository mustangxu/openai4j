/**
 *
 */
package com.jayxu.openai4j;

import java.io.Serial;

import com.jayxu.openai4j.model.ServiceError;

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
    ServiceError error;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return this.error == null ? null : this.error.getMessage();
    }

}
