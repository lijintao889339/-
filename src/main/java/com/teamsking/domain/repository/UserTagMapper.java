package com.teamsking.domain.repository;

import com.teamsking.domain.entity.tag.UserTag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserTagMapper extends Mapper<UserTag> {

    int insertUserTagByUserTags(@Param("userTagList") List<UserTag> userTagList);

}