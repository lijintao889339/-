package com.teamsking.domain.service;

import com.teamsking.domain.entity.CourseItem;
import com.teamsking.domain.repository.CourseItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseItemService {

    @Autowired
    CourseItemMapper courseItemMapper;

    /**
     * 获取课程中章节项的列表
     * @param courseItem
     * @return
     */
    public List<CourseItem> list(CourseItem courseItem){

        return courseItemMapper.select(courseItem);
    }

    /**
     * 添加课程中的章节项
     * @param courseItem
     * @return
     */
    public int save(CourseItem courseItem){

        return courseItemMapper.insert(courseItem);
    }

    /**
     * 删除课程中的章节项
     * @param courseItem
     * @return
     */
    public int remove(CourseItem courseItem){

        return courseItemMapper.delete(courseItem);
    }

    /**
     * 修改课程中的章节项
     * @param courseItem
     * @return
     */
    public int modify(CourseItem courseItem){

        return courseItemMapper.updateByPrimaryKeySelective(courseItem);
    }

}



