package com.teamsking.api.dto.announce;

import com.teamsking.domain.entity.announce.AnnounceUser;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface AnnounceUserDtoMapper {

    AnnounceUserDtoMapper INSTANCE = Mappers.getMapper(AnnounceUserDtoMapper.class);

    List<AnnounceUserDto> entityListToDtoList(List<AnnounceUser> entityList);

    AnnounceUserDto entityToDto(AnnounceUser announceUser);

    AnnounceUser dtoToEntity(AnnounceUserDto announceUserDto);

}
