package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.sys.UserTeacherGroup;
import com.teamsking.domain.repository.UserTeacherGroupMapper;
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
public class UserTeacherGroupService extends BaseService {

    @Autowired
    UserTeacherGroupMapper userTeacherGroupMapper;


    /**
     * 根据辅导老师Ids获取老师和分组信息
     * @param userTeacherIds
     * @return
     */
    public List<UserTeacherGroup> getTeacherGroupInfoByTeacherIds(List<Integer> userTeacherIds) {

        Example teacherGroupExample = new Example(UserTeacherGroup.class);
        teacherGroupExample.and().andIn("userTeacherId",userTeacherIds);
        return userTeacherGroupMapper.selectByExample(teacherGroupExample);
    }


    /**
     * 根据分组Ids获取老师和分组信息
     * @param groupIds
     * @return
     */
    public List<UserTeacherGroup> getTeacherGroupInfoByGroupIds(List<Integer> groupIds) {

        Example teacherGroupExample = new Example(UserTeacherGroup.class);
        teacherGroupExample.and().andIn("groupId",groupIds);
        return userTeacherGroupMapper.selectByExample(teacherGroupExample);
    }
}
