package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class ApplicationAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Application already exists!";

    public ApplicationAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
