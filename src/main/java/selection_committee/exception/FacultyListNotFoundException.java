package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class FacultyListNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Faculty's list is not found!";

    public FacultyListNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
