package com.teamsking.api.dto.announce;

import com.teamsking.domain.entity.announce.Announce;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface AnnounceDtoMapper {

    AnnounceDtoMapper INSTANCE = Mappers.getMapper(AnnounceDtoMapper.class);

    List<AnnounceDto> entityListToDtoList(List<Announce> entityList);

    AnnounceDto entityToDto(Announce announce);

    Announce dtoToEntity(AnnounceDto announceDto);

}
