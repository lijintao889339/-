package com.teamsking.api.dto.sys;

import com.google.common.collect.Lists;
import com.teamsking.domain.entity.sys.UserTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface UserTeacherDtoMapper {

    UserTeacherDtoMapper INSTANCE = Mappers.getMapper(UserTeacherDtoMapper.class);

    List<UserDto> entityDtoToUserDtoList(List<UserTeacher> userTeacherList);

    List<UserTeacherDto> entityDtoToTeacherDtoList(List<UserTeacher> userTeacherList);

    List<UserTeacherNameDto> entityListToTeacherNameDtoList(List<UserTeacher> userTeacherLists);

}
