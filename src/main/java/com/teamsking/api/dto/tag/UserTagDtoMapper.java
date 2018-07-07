package com.teamsking.api.dto.tag;

import com.teamsking.domain.entity.tag.UserTag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserTagDtoMapper {

    UserTagDtoMapper INSTANCE = Mappers.getMapper(UserTagDtoMapper.class);

    List<UserTagDto> entityListToDtoList(List<UserTag> entityList);

    UserTagDto entityToDto(UserTag userTag);

    UserTag dtoToEntity(UserTagDto userTagDto);

}
