package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.repository.OpenUserMapper;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenUserService extends BaseService {

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

    /**
     * 删除与某班课有关的班课用户关系数据
     * @param openId
     * @return
     */
    public int removeOpenUserByOpenId(Integer openId) {

        Example openUserExample = new Example(OpenUser.class);
        openUserExample.and().andEqualTo("openId",openId);
        openUserExample.and().andEqualTo("deleteStatus",2);
        return openUserMapper.deleteByExample(openUserExample);
    }

    /**
     * 根据openIds查询用户选课列表
     * @param openIds
     * @return
     */
    public List<OpenUser> getOpenUserListByOpenIds(List<Integer> openIds) {

        Example openUserExample = new Example(OpenUser.class);
        openUserExample.and().andIn("openId",openIds);
        openUserExample.and().andEqualTo("deleteStatus",2);
        return openUserMapper.selectByExample(openUserExample);
    }

    /**
     * 根据学生ids获取用户选课列表
     * @param userStudentIds
     * @param openId
     * @return
     */
    public List<OpenUser> getOpenUserLIstByStudentIds(List<Integer> userStudentIds, Integer openId) {

        Example openUserExample = new Example(OpenUser.class);
        openUserExample.and().andIn("userStudentId",userStudentIds);
        openUserExample.and().andEqualTo("deleteStatus",2);
        openUserExample.and().andEqualTo("openId",openId);
        return openUserMapper.selectByExample(openUserExample);
    }
}
