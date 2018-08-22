package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.repository.UserStudentMapper;
import com.teamsking.domain.repository.UserTeacherMapper;
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
public class UserStudentService extends BaseService {

    @Autowired
    UserStudentMapper userStudentMapper;

    /**
     * 根据学生Ids获取学生信息
     * @param studentIds
     * @return
     */
    public List<UserStudent> getUserStudentListByIds(List<Integer> studentIds) {

        Example studentExample = new Example(UserStudent.class);
        studentExample.and().andIn("id",studentIds);
        studentExample.and().andEqualTo("deleteStatus",2);
        return userStudentMapper.selectByExample(studentExample);
    }

    public List<UserStudent> getUserStudentListByUserIds(List<Integer> userIds) {

        Example studentExample = new Example(UserStudent.class);
        studentExample.and().andIn("userId",userIds);
        studentExample.and().andEqualTo("deleteStatus",2);
        return userStudentMapper.selectByExample(studentExample);
    }
}
