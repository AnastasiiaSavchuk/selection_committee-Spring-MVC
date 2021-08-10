package selection_committee.dto;

import lombok.Builder;
import lombok.Data;
import selection_committee.model.Application;
import selection_committee.model.Grade;

@Data
@Builder
public class ApplicationGradeDto {

    private Application application;
    private Grade grade;
}
