package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.FacultyDto;
import selection_committee.model.Faculty;

import java.util.List;

@Mapper
public interface FacultyMapper {
    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    Faculty mapToFaculty(FacultyDto dto);

    FacultyDto mapToFacultyDto(Faculty faculty);

    List<FacultyDto> mapToListDto(List<Faculty> list);
}
