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
     * 根据课程Id删除班课和教学老师关系信息
     * @param openId
     * @return
     */
    public int removeOpenUserTeacherByOpenId(Integer openId) {

        Example userTeacherExample = new Example(OpenUserTeacher.class);
        userTeacherExample.and().andEqualTo("openId",openId);
        return openUserTeacherMapper.deleteByExample(userTeacherExample);
    }
}
