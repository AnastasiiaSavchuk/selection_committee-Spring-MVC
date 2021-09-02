package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class ApplicationNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Application is not found!";

    public ApplicationNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
