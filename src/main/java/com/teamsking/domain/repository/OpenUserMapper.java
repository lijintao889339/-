package com.teamsking.domain.repository;

import com.teamsking.domain.entity.open.OpenUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface OpenUserMapper extends Mapper<OpenUser> {

    List<Map<String,Object>> countByOpenIdsGroupByOpenId(@Param("openIds") List<Integer> openIds);
    //List<OpenUser> countByOpenIdByUserId(@Param("openId") Integer openId);
    //int insertConnectionByOpenAndUser(@Param("openUserList") List<OpenUser> openUserList);
}