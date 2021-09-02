package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class FacultySubjectAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Subject is already adds to the faculty!";

    public FacultySubjectAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
