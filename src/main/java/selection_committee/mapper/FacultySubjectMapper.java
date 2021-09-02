package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.FacultySubjectDto;
import selection_committee.model.FacultySubject;

import java.util.List;

@Mapper
public interface FacultySubjectMapper {
    FacultySubjectMapper INSTANCE = Mappers.getMapper(FacultySubjectMapper.class);

    FacultySubjectDto mapToFacultySubjectDto(FacultySubject facultySubject);

    List<FacultySubjectDto> mapToListDto(List<FacultySubject> list);
}
