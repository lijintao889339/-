package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenCover;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenCoverDtoMapper {

    OpenCoverDtoMapper INSTANCE = Mappers.getMapper(OpenCoverDtoMapper.class);

    List<OpenCoverDto> entityListToDtoList(List<OpenCover> entityList);

    OpenCoverDto entityToDto(OpenCover openCover);

    OpenCover dtoToEntity(OpenCoverDto openCoverDto);

}
