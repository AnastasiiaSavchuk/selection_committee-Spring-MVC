package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class SubjectListNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Subject's list is not found!";

    public SubjectListNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
