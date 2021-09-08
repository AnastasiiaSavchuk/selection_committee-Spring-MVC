package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class FacultySubjectListNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "List of Subject by faculty is not found!";

    public FacultySubjectListNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
