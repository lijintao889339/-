package com.teamsking.api.dto.study;

import com.teamsking.domain.entity.study.StudyScore;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyScoreDtoMapper {

    StudyScoreDtoMapper INSTANCE = Mappers.getMapper(StudyScoreDtoMapper.class);

    List<StudyScoreDto> entityListToDtoList(List<StudyScore> entityList);

    StudyScoreDto entityToDto(StudyScore studyScore);

    StudyScore dtoToEntity(StudyScoreDto studyScoreDto);

}
