package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.repository.OpenUserMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenUserService {

    @Autowired
    OpenUserMapper openUserMapper;

    /**
     *班次-学生选课列表
     * @return
     */
    public List<OpenUser> list(){

        return openUserMapper.selectAll();
    }

    /**
     *添加班次-学生选课
     * @param openUser
     * @return
     */
    public int save(OpenUser openUser){

        return openUserMapper.insert(openUser);
    }

    /**
     *删除班次-学生选课
     * @param id
     * @return
     */
    public int remove(int id){

        return openUserMapper.deleteByPrimaryKey(id);
    }

    /**
     *修改班次-学生选课
     * @param openUser
     * @return
     */
    public int modify(OpenUser openUser){

        return openUserMapper.updateByPrimaryKeySelective(openUser);
    }

    /**
     * 根据openId查询用户选课列表
     * @param openId
     * @return
     */
    public List<OpenUser> getOpenUserByOpenId(Integer openId) {

        OpenUser openUser = new OpenUser();
        openUser.setId(openId);
        return openUserMapper.select(openUser);
    }
}
