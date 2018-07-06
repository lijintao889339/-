package com.teamsking.domain.service.school;

import com.teamsking.domain.entity.school.SchoolCarousel;
import com.teamsking.domain.entity.school.SchoolFriendship;
import com.teamsking.domain.repository.SchoolFriendshipMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SchoolFriendshipService {

    @Autowired
    SchoolFriendshipMapper schoolFriendshipMapper;

    /**
     * 获取学校-友情链接管理列表
     * @return
     */
    public List<SchoolFriendship> list(){

        return schoolFriendshipMapper.selectAll();
    }

    /**
     * 添加学校-友情链接管理
     * @param schoolFriendship
     * @return
     */
    public int save(SchoolFriendship schoolFriendship){

        return schoolFriendshipMapper.insert(schoolFriendship);
    }

    /**
     * 删除学校-友情链接管理
     * @param id
     * @return
     */
    public int remove(int id){

        return schoolFriendshipMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改学校-友情链接管理
     * @param schoolFriendship
     * @return
     */
    public int modify(SchoolFriendship schoolFriendship){

        return schoolFriendshipMapper.updateByPrimaryKeySelective(schoolFriendship);
    }
}
