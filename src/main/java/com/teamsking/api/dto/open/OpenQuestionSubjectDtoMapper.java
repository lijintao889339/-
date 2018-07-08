package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenQuestionSubject;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenQuestionSubjectDtoMapper {

    OpenQuestionSubjectDtoMapper INSTANCE = Mappers.getMapper(OpenQuestionSubjectDtoMapper.class);

    List<OpenQuestionSubjectDto> entityListToDtoList(List<OpenQuestionSubject> entityList);

    OpenQuestionSubjectDto EntityToDto(OpenQuestionSubject openQuestionSubject);

    OpenQuestionSubject dtoToEntity(OpenQuestionSubjectDto openQuestionSubjectDto);

}
