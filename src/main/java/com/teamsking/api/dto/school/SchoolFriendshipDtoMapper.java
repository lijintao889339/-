package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.entity.school.SchoolFriendship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface SchoolFriendshipDtoMapper {

    SchoolFriendshipDtoMapper INSTANCE = Mappers.getMapper(SchoolFriendshipDtoMapper.class);

    List<SchoolFriendshipDto> entityListToDtoList(List<SchoolFriendship> entityList);

    SchoolFriendshipDto entityToDto(SchoolFriendship schoolFriendship);

    SchoolFriendship dtoToEntity(SchoolFriendshipDto schoolFriendshipDto);

}
