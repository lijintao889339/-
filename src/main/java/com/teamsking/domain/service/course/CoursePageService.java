package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CoursePage;
import com.teamsking.domain.repository.CoursePageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CoursePageService {

    @Autowired
    CoursePageMapper coursePageMapper;

    /**
     * 获取课程-章节页面
     * @return
     */
    public List<CoursePage> list(){

        return coursePageMapper.selectAll();
    }

    /**
     * 添加课程-章节页面
     * @param coursePage
     * @return
     */
    public int save(CoursePage coursePage){

        return coursePageMapper.insert(coursePage);
    }

    /**
     * 删除课程-章节页面
     * @param id
     * @return
     */
    public int remove(int id){

        return coursePageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程-章节页面
     * @param coursePage
     * @return
     */
    public int modify(CoursePage coursePage){

        return coursePageMapper.updateByPrimaryKeySelective(coursePage);
    }


}
