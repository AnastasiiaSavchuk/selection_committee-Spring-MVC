package selection_committee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import selection_committee.model.enums.ErrorType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;

    private ErrorType errorType;

    private LocalDateTime timeStamp;
}
