package com.teamsking.api.dto;


import com.teamsking.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserDtoMapper {

    SysUserDtoMapper INSTANCE = Mappers.getMapper(SysUserDtoMapper.class);

    List<SysUserDto> entityListToDtoList(List<SysUser> entityList);

    SysUserDto entityToDto(SysUser sysUser);

    SysUser dtoToEntity(SysUserDto sysUserDto);



}
