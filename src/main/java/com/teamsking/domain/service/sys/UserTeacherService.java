package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.sys.UserTeacher;
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
@Slf4j
@Service
public class UserTeacherService extends BaseService {

    @Autowired
    UserTeacherMapper userTeacherMapper;

    public List<UserTeacher> getUserTeacherListByIds(List<Integer> userTeacherIdList) {

        Example userTeacherExample = new Example(UserTeacher.class);
        userTeacherExample.and().andIn("id",userTeacherIdList);
        return userTeacherMapper.selectByExample(userTeacherExample);
    }
}
