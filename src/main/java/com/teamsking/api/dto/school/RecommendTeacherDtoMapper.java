package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.RecommendOpen;
import com.teamsking.domain.entity.school.RecommendTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface RecommendTeacherDtoMapper {

    RecommendTeacherDtoMapper INSTANCE = Mappers.getMapper(RecommendTeacherDtoMapper.class);

    List<RecommendTeacherDto> entityListToDtoList(List<RecommendTeacher> entityList);

    RecommendTeacherDto entityToDto(RecommendTeacher recommendTeacher);

    RecommendTeacher dtoToEntity(RecommendTeacherDto recommendTeacherDto);

}
