package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.Open;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenDtoMapper {

    OpenDtoMapper INSTANCE = Mappers.getMapper(OpenDtoMapper.class);

    List<OpenDto> entityListToDtoList(List<Open> entityList);

    OpenDto entityToDto(Open open);

    Open dtoToEntity(OpenDto openDto);

    OpenListViewDto entityToListViewDto(Open open);

}
