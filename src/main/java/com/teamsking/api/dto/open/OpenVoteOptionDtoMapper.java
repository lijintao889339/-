package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenVoteOption;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenVoteOptionDtoMapper {

    OpenVoteOptionDtoMapper INSTANCE = Mappers.getMapper(OpenVoteOptionDtoMapper.class);

    List<OpenVoteOptionDto> entityListToDtoList(List<OpenVoteOption> entityList);

    OpenVoteOptionDto entityToDto(OpenVoteOption openVoteOption);

    OpenVoteOption dtoToEntity(OpenVoteOptionDto openVoteOptionDto);

}
