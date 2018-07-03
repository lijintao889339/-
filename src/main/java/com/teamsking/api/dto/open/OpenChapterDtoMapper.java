package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenChapter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface OpenChapterDtoMapper {


    OpenChapterDtoMapper INSTANCE = Mappers.getMapper(OpenChapterDtoMapper.class);

    List<OpenChapterDto> entityListToDtoList(List<OpenChapter> entityList);

    OpenChapterDto entityToDto(OpenChapter openChapter);

    OpenChapter dtoToEntity(OpenChapterDto openChapterDto);

}
