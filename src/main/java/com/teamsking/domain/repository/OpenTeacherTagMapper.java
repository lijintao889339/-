package com.teamsking.domain.repository;

import com.teamsking.domain.entity.open.OpenTeacherTag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OpenTeacherTagMapper extends Mapper<OpenTeacherTag> {
    int insertOpenTeacherTagByTags(@Param("openTeacherTagList") List<OpenTeacherTag> openTeacherTagList);
}