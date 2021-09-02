package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.SubjectDto;
import selection_committee.model.Subject;

import java.util.List;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject mapToSubject(SubjectDto dto);

    SubjectDto mapToSubjectDto(Subject subject);

    List<SubjectDto> mapToListDto(List<Subject> list);
}
