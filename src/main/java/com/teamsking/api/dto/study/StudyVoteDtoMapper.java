package com.teamsking.api.dto.study;

import com.teamsking.domain.entity.study.StudyVote;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyVoteDtoMapper {

    StudyVoteDtoMapper INSTANCE = Mappers.getMapper(StudyVoteDtoMapper.class);

    List<StudyVoteDto> entityListToDtoList(List<StudyVote> entityList);

    StudyVoteDto entityToDto(StudyVote studyVote);

    StudyVote dtoToEntity(StudyVoteDto studyVoteDto);


}
