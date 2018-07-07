package com.teamsking.domain.service.tag;

import com.teamsking.domain.entity.tag.UserTag;
import com.teamsking.domain.repository.UserTagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserTagService {


    @Autowired
    UserTagMapper userTagMapper;


    /**
     * 获取标签-用户关系管理
     * @return
     */
    public List<UserTag> list(){

        return userTagMapper.selectAll();

    }

    /**
     * 添加标签-用户关系管理
     * @param userTag
     * @return
     */
    public int save(UserTag userTag){

        return userTagMapper.insert(userTag);

    }


    /**
     * 删除标签-用户关系管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return userTagMapper.deleteByPrimaryKey(id);


    }


    /**
     * 修改标签-用户关系管理
     * @param userTag
     * @return
     */
    public int modify(UserTag userTag){

        return userTagMapper.updateByPrimaryKeySelective(userTag);

    }

}
