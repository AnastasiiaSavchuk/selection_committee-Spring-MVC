package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class GradeListNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Grade's list is not found!";

    public GradeListNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
