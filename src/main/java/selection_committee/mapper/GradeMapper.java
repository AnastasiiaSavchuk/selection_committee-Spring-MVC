package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.GradeDto;
import selection_committee.model.Grade;

import java.util.List;

@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    Grade mapToGrade(GradeDto dto);

    GradeDto mapToGradeDto(Grade grade);

    List<GradeDto> mapToListDto(List<Grade> list);
}
