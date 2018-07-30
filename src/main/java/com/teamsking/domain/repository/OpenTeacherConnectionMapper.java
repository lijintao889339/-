package com.teamsking.domain.repository;

import com.teamsking.domain.entity.open.OpenTeacherConnection;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OpenTeacherConnectionMapper extends Mapper<OpenTeacherConnection> {
    int insertConnectionByOpenAndTeachers(@Param("openTeacherConnectionList") List<OpenTeacherConnection> openTeacherConnectionList);
}