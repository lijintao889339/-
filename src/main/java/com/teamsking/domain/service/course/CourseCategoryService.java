package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseCategoryDto;
import com.teamsking.api.dto.course.CourseCategoryDtoMapper;
import com.teamsking.domain.entity.course.CourseCategory;
import com.teamsking.domain.repository.CourseCategoryMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseCategoryService extends BaseService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page list(int pageNo, int pageSize) {

        //获取所有的一级课程分类
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setDeleteStatus(2);
        courseCategory.setParentId(0);

        PageHelper.startPage(pageNo,pageSize);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.select(courseCategory);

        //数据转换
        List<CourseCategoryDto> courseCategoryDtoList = CourseCategoryDtoMapper.INSTANCE.entityListToDtoList(courseCategoryList);
        return convertPage((Page)courseCategoryList,courseCategoryDtoList);
    }

    /**
     * 根据一级分类id获取二级课程分类
     * @param id
     * @return
     */
    public List<CourseCategoryDto> getSecondCourseCategory(int id) {

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setDeleteStatus(2);
        courseCategory.setParentId(id);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.select(courseCategory);

        //数据转换
        List<CourseCategoryDto> courseCategoryDtoList = CourseCategoryDtoMapper.INSTANCE.entityListToDtoList(courseCategoryList);
        return courseCategoryDtoList;
    }
}
