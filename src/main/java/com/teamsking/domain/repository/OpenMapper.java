package com.teamsking.domain.repository;

import com.teamsking.domain.entity.open.Open;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OpenMapper extends Mapper<Open> {

    List<Map<String, Object>> countByCourseIdsGroupByCourseId(@Param("courseIds") List<Integer> courseIds);
}