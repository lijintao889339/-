package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenUserTeacher;
import com.teamsking.domain.repository.OpenUserTeacherMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenUserTeacherService extends BaseService {

    @Autowired
    OpenUserTeacherMapper openUserTeacherMapper;

    /**
     * 根据班课Id删除班课和教学老师关系信息
     * @param openId
     * @return
     */
    public int removeOpenUserTeacherByOpenId(Integer openId) {

        OpenUserTeacher openUserTeacher = new OpenUserTeacher();
        openUserTeacher.setDeleteStatus(1);

        Example userTeacherExample = new Example(OpenUserTeacher.class);
        userTeacherExample.and().andEqualTo("openId",openId);
        return openUserTeacherMapper.updateByExample(openUserTeacher,userTeacherExample);
    }

    /**
     * 根据班组ids获取老师班组分组信息
     * @param groupIds
     * @return
     */
    public List<OpenUserTeacher> getTeacherGroupInfoByGroupIds(List<Integer> groupIds) {

        Example userTeacherExample = new Example(OpenUserTeacher.class);
        userTeacherExample.and().andEqualTo("deleteStatus",2);
        userTeacherExample.and().andIn("groupId",groupIds);
        return openUserTeacherMapper.selectByExample(userTeacherExample);
    }

    /**
     * 根据班组Ids删除班课和教学老师关系信息
     * @param openGroupIds
     * @param openId
     * @return
     */
    public int removeUserTeacherGroupByGroupIds(List<Integer> openGroupIds, int openId) {

        OpenUserTeacher openUserTeacher = new OpenUserTeacher();
        openUserTeacher.setDeleteStatus(1);

        Example teacherGroupExample = new Example(OpenUserTeacher.class);
        teacherGroupExample.and().andIn("groupId",openGroupIds);
        teacherGroupExample.and().andEqualTo("openId",openId);
        return openUserTeacherMapper.updateByExampleSelective(openUserTeacher,teacherGroupExample);
    }
}
