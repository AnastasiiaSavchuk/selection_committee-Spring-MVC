package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class StatementAlreadyRollbackException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Statement already rollback!";

    public StatementAlreadyRollbackException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
