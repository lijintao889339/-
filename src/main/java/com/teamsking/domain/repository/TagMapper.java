package com.teamsking.domain.repository;

import com.teamsking.domain.entity.tag.Tag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {

    int insertTagByTags(List<Tag> tagList);

}