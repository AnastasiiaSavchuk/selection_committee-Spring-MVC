package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class SubjectAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Subject already exists!";

    public SubjectAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
