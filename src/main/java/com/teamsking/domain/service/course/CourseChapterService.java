package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseChapter;
import com.teamsking.domain.repository.CourseChapterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseChapterService {

    @Autowired
    CourseChapterMapper courseChapterMapper;

    /**
     * 获取课程中章的列表
     * @return 章列表
     */
    public List<CourseChapter> list(){

        return courseChapterMapper.selectAll();
    }

    /**
     * 添加课程中的章
     * @param courseChapter
     * @return
     */
    public int save(CourseChapter courseChapter){

        return courseChapterMapper.insert(courseChapter);
    }

    /**
     * 删除课程中的章
     * @param id
     * @return
     */
    public int remove(int id){

        return courseChapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程中的章
     * @param courseChapter
     * @return
     */
    public int modify(CourseChapter courseChapter){

        return courseChapterMapper.updateByPrimaryKeySelective(courseChapter);
    }

}
