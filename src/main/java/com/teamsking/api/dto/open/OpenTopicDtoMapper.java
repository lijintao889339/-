package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenTopic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenTopicDtoMapper {

    OpenTopicDtoMapper INSTANCE = Mappers.getMapper(OpenTopicDtoMapper.class);

    List<OpenTopicDto> entityListToDtoList(List<OpenTopic> entityList);

    OpenTopicDto entityToDto(OpenTopic openTopic);

    OpenTopic dtoToEntity(OpenTopicDto openTopicDto);


}
