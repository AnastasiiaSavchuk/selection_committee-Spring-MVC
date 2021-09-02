package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class GradeNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Grade is not found!";

    public GradeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
