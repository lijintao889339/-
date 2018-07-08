package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenItemDtoMapper {

    OpenItemDtoMapper INSTANCE = Mappers.getMapper(OpenItemDtoMapper.class);

    List<OpenItemDto> entityListToDtoList(List<OpenItem> entityList);

    OpenItemDto entityToDto(OpenItem openItem);

    OpenItem dtoToEntity(OpenItemDto openItemDto);

}
