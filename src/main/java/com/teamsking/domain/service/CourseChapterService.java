package com.teamsking.domain.service;

import com.teamsking.domain.entity.CourseChapter;
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
     * @param courseChapter
     * @return 章列表
     */
    public List<CourseChapter> list(CourseChapter courseChapter){

        return courseChapterMapper.select(courseChapter);
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
     * @param courseChapter
     * @return
     */
    public int remove(CourseChapter courseChapter){

        return courseChapterMapper.delete(courseChapter);
    }

    /**
     * 根据主键选择性修改课程中的章
     * @param courseChapter
     * @return
     */
    public int modify(CourseChapter courseChapter){

        return courseChapterMapper.updateByPrimaryKeySelective(courseChapter);
    }

}
