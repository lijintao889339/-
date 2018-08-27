package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenVote;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenVoteDtoMapper {

    OpenVoteDtoMapper INSTANCE = Mappers.getMapper(OpenVoteDtoMapper.class);

    List<OpenVoteDto> entityListToDtoList(List<OpenVote> entityList);

    OpenVoteDto entityToDto(OpenVote openVote);

    OpenVote dtoToEntity(OpenVoteDto openVoteDto);

    List<OpenVoteQueryDto> entityListToQueryDotList(List<OpenVote> openVotes);
}
