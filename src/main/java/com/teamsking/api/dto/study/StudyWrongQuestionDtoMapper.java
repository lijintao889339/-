package com.teamsking.api.dto.study;

import com.teamsking.domain.entity.study.StudyWrongQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudyWrongQuestionDtoMapper {

    StudyWrongQuestionDtoMapper INSTANCE = Mappers.getMapper(StudyWrongQuestionDtoMapper.class);

    List<StudyWrongQuestionDto> entityListToDtoList(List<StudyWrongQuestion> entityList);

    StudyWrongQuestionDto entityToDto(StudyWrongQuestion studyWrongQuestion);

    StudyWrongQuestion dtoToEntity(StudyWrongQuestionDto studyWrongQuestionDto);


}
