package selection_committee.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import selection_committee.model.enums.ErrorType;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceException extends RuntimeException {

    private ErrorType errorType;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
