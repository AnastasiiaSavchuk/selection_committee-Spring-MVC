package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class FacultyAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Faculty already exists!";

    public FacultyAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
