package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseItem;
import com.teamsking.domain.repository.CourseItemMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return
     */
    public List<CourseItem> list( ){

        return courseItemMapper.selectAll();
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
     * @param id
     * @return
     */
    public int remove(int id){

        return courseItemMapper.deleteByPrimaryKey(id);
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



