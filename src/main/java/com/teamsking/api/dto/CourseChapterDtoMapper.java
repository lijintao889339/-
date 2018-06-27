package com.teamsking.api.dto;

import com.teamsking.domain.entity.CourseChapter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseChapterDtoMapper {

    CourseChapterDtoMapper INSTANCE = Mappers.getMapper(CourseChapterDtoMapper.class);

    List<CourseChapterDto> entityListToDtoList(List<CourseChapter> entityList);

    CourseChapterDto entityToDto(CourseChapter courseChapter);

    CourseChapter dtoToEntity(CourseChapterDto courseChapterDto);

}
