package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class FacultySubjectNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Subject in faculty is not found!";

    public FacultySubjectNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
