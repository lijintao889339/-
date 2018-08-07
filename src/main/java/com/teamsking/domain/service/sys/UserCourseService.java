package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.course.UserCourse;
import com.teamsking.domain.repository.UserCourseMapper;
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
public class UserCourseService extends BaseService {

    @Autowired
    UserCourseMapper userCourseMapper;

    /**
     * 删除与某课程有关的课程用户关系数据
     * @param courseId
     * @return
     */
    public int removeUserCourseByCourseId(Integer courseId) {

        Example userCourseExample = new Example(UserCourse.class);
        userCourseExample.and().andEqualTo("courseId",courseId);
        return userCourseMapper.deleteByExample(userCourseExample);

    }
}
