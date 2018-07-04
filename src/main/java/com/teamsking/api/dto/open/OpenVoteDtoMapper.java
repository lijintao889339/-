package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenVote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenVoteDtoMapper {

    OpenVoteDtoMapper INSTANCE = Mappers.getMapper(OpenVoteDtoMapper.class);

    List<OpenVoteDto> entityListToDtoList(List<OpenVote> entityList);

    OpenVoteDto entityToDto(OpenVote openVote);

    OpenVote dtoToEntity(OpenVoteDto openVoteDto);


}
