package com.teamsking.api.dto.study;


import com.teamsking.domain.entity.study.StudyNote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudyNoteDtoMapper {

    StudyNoteDtoMapper INSTANCE = Mappers.getMapper(StudyNoteDtoMapper.class);

    List<StudyNoteDto> entityListToDtoList(List<StudyNote> entityList);

    StudyNoteDto entityToDto(StudyNote studyNote);

    StudyNote dtoToEntity(StudyNoteDto studyNoteDto);

}
