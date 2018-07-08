package com.teamsking.api.dto.study;

import com.teamsking.domain.entity.study.StudyQuestionnaire;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyQuestionnaireDtoMapper {

    StudyQuestionnaireDtoMapper INSTANCE = Mappers.getMapper(StudyQuestionnaireDtoMapper.class);

    List<StudyQuestionnaireDto> entityListToDtoList(List<StudyQuestionnaire> entityList);

    StudyQuestionnaireDto entityToDto(StudyQuestionnaire studyQuestionnaire);

    StudyQuestionnaire dtoToEntity(StudyQuestionnaireDto studyQuestionnaireDto);


}
