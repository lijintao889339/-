package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.CourseCategoryDto;
import com.teamsking.api.dto.course.CourseCategoryDtoMapper;
import com.teamsking.domain.entity.course.CourseCategory;
import com.teamsking.domain.repository.CourseCategoryMapper;
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
public class CourseCategoryService extends BaseService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    /**
     *获取一级课程分类
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

    /**
     * 新增二级课程分类
     * @param courseCategoryDto
     * @param id
     * @return
     */
    public int saveSecondCourseCategory(CourseCategoryDto courseCategoryDto, int id) {

        //先查询该一级分类下面有几个二级分类
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setParentId(id);
        int count = courseCategoryMapper.selectCount(courseCategory);

        //添加新的二级分类
        CourseCategory newCourseCategory = new CourseCategory();
        newCourseCategory.setDeleteStatus(2);
        newCourseCategory.setParentId(id);
        newCourseCategory.setDisplayOrder(count + 1);
        newCourseCategory.setIsFirstLabel(false);
        newCourseCategory.setIsShow(true);
        newCourseCategory.setLabel(courseCategoryDto.getLabel());
        return courseCategoryMapper.insertSelective(newCourseCategory);

    }

    /**
     * 编辑课程分类
     * @param courseCategoryDto
     * @param id
     * @return
     */
    public int modifyCourseCategory(CourseCategoryDto courseCategoryDto, int id) {

        CourseCategory courseCategory = CourseCategoryDtoMapper.INSTANCE.dtoToEntity(courseCategoryDto);
        courseCategory.setId(id);
        return courseCategoryMapper.updateByPrimaryKeySelective(courseCategory);

    }

    /**
     * 删除课程分类
     * @param id
     * @return
     */
    public int removeCourseCategory(int id) {

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setDeleteStatus(1);

        Example courseCategoryExample = new Example(CourseCategory.class);
        courseCategoryExample.or().andEqualTo("id",id);
        courseCategoryExample.or().andEqualTo("parentId",id);
        return courseCategoryMapper.updateByExampleSelective(courseCategory,courseCategoryExample);
    }

    public int removeCourseCategoryByIds(Integer[] ids) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids){
            idList.add(id);
        }

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setDeleteStatus(1);

        Example courseCategoryExample = new Example(CourseCategory.class);
        Example.Criteria cri = courseCategoryExample.createCriteria();
        cri.orIn("id",idList);
        cri.orIn("parentId",idList);
        return courseCategoryMapper.updateByExampleSelective(courseCategory,courseCategoryExample);

    }

    /**
     * 是否显示课程分类
     * @param courseCategoryDto
     * @param id
     */
    public int isShowById(CourseCategoryDto courseCategoryDto, int id) {

        CourseCategory courseCategory = new CourseCategory();

        if (courseCategoryDto.getIsShow()){
            courseCategory.setIsShow(true);
        }else {
            courseCategory.setIsShow(false);
        }

        Example courseCategoryExample = new Example(CourseCategory.class);
        courseCategoryExample.or().andEqualTo("id",id);
        courseCategoryExample.or().andEqualTo("parentId",id);
        return courseCategoryMapper.updateByExampleSelective(courseCategory,courseCategoryExample);
    }
}
