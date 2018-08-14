package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.sys.UserStudent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface UserStudentDtoMapper {

    UserStudentDtoMapper INSTANCE = Mappers.getMapper(UserStudentDtoMapper.class);

    List<UserStudentDto> entityListToDtoList(List<UserStudent> userStudentList);

}
