package com.teamsking.api.dto.tag;

import com.teamsking.domain.entity.tag.Tag;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagDtoMapper {

    TagDtoMapper INSTANCE = Mappers.getMapper(TagDtoMapper.class);

    List<TagDto> entityListToDtoList(List<Tag> entityList);

    TagDto entityToDto(Tag tag);

    Tag dtoToEntity(TagDto tagDto);


}
