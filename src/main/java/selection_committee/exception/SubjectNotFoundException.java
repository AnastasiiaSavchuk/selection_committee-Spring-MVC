package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class SubjectNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Subject is not found!";

    public SubjectNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
