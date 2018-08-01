package com.teamsking.domain.repository;

import com.teamsking.domain.entity.open.OpenRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OpenRoleMapper extends Mapper<OpenRole> {
    int insertConnectionByOpenAndRole(@Param("openRoleList") List<OpenRole> openRoleList);
}