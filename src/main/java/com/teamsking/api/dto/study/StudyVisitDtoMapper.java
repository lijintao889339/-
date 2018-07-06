package com.teamsking.api.dto.study;

import com.teamsking.domain.entity.study.StudyVisit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudyVisitDtoMapper {

    StudyVisitDtoMapper INSTANCE = Mappers.getMapper(StudyVisitDtoMapper.class);

    List<StudyVisitDto> entityListToDtoList(List<StudyVisit> entityList);

    StudyVisitDto entityToDto(StudyVisit studyVisit);

    StudyVisit dtoToEntity(StudyVisitDto studyVisitDto);

}
