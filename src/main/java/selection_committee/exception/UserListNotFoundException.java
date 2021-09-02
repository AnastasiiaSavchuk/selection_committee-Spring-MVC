package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class UserListNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "User's list is not found!";

    public UserListNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
