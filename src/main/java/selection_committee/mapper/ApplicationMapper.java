package selection_committee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import selection_committee.dto.ApplicationDto;
import selection_committee.model.Application;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application mapToApplication(ApplicationDto dto);

    ApplicationDto mapToApplicationDto(Application application);

    List<ApplicationDto> mapToListDto(List<Application> list);
}
