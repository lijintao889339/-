package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseSection;
import com.teamsking.domain.repository.CourseSectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseSectionService {

    @Autowired
    CourseSectionMapper courseSectionMapper;

    /**
     * 获取课程中节的列表
     * @return
     */
    public List<CourseSection> list(){

        return courseSectionMapper.selectAll();
    }

    /**
     * 添加课程中的节
     * @param courseSection
     * @return
     */
    public int save(CourseSection courseSection){

        return courseSectionMapper.insert(courseSection);
    }

    /**
     * 删除课程中的节
     * @param id
     * @return
     */
    public int remove(int id){

        return courseSectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程中的节
     * @param courseSection
     * @return
     */
    public int modify(CourseSection courseSection){

        return courseSectionMapper.updateByPrimaryKeySelective(courseSection);
    }

}
