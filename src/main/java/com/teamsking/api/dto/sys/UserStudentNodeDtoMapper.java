package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.UserStudentNode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/

@Mapper
public interface UserStudentNodeDtoMapper {

    UserStudentNodeDtoMapper INSTANCE = Mappers.getMapper(UserStudentNodeDtoMapper.class);

    List<UserStudentNodeDto> entityListToDtoList(List<UserStudentNode> entityList);

}
