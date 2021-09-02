package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class GradeAlreadyExistsException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Grade already exists!";

    public GradeAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
