package selection_committee.exception;

import selection_committee.model.enums.ErrorType;

public class StatementAlreadyCreateException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Statement already create!";

    public StatementAlreadyCreateException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
