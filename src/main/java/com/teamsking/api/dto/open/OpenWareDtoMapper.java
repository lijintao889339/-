package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenWare;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenWareDtoMapper {

    OpenWareDtoMapper INSTANCE = Mappers.getMapper(OpenWareDtoMapper.class);

    List<OpenWareDto> entityListToDtoList(List<OpenWare> entityList);

    OpenWareDto entityToDto(OpenWare openWare);

    OpenWare dtoToEntity(OpenWareDto openWareDto);

}
