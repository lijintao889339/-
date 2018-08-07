package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.CourseCategoryDto;
import com.teamsking.api.dto.course.CourseCategoryDtoMapper;
import com.teamsking.api.dto.course.CourseCategoryNameDto;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseCategory;
import com.teamsking.domain.repository.CourseCategoryMapper;
import com.teamsking.domain.repository.CourseMapper;
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
    @Autowired
    CourseMapper courseMapper;

    /**
     *获取一级课程模板分类
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page list(int pageNo, int pageSize) {

        //获取所有的一级课程模板分类
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setDeleteStatus(2);
        courseCategory.setParentId(0);

        PageHelper.startPage(pageNo,pageSize);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.select(courseCategory);

        //数据转换
        List<CourseCategoryDto> courseCategoryDtoList = CourseCategoryDtoMapper.INSTANCE.entityListToDtoList(courseCategoryList);

        //获取一级分类下的课程模板数量
        for (CourseCategoryDto courseCategoryDto : courseCategoryDtoList){
            //查询该一级分类下课程模板数量
            Course course = new Course();
            course.setFirstCategoryId(courseCategoryDto.getId());
            int count = courseMapper.selectCount(course);
            courseCategoryDto.setCourseCount(count);
        }

        return convertPage((Page)courseCategoryList,courseCategoryDtoList);
    }

    /**
     * 根据一级分类id获取二级课程模板分类
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

        for (CourseCategoryDto courseCategoryDto : courseCategoryDtoList){
            //查询该分类下课程模板数量
            Course course = new Course();
            course.setCategoryId(courseCategoryDto.getId());
            int count = courseMapper.selectCount(course);
            courseCategoryDto.setCourseCount(count);
        }
        return courseCategoryDtoList;
    }

    /**
     * 新增二级课程模板分类
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
     * 编辑课程模板分类
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
     * 删除课程模板分类
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

    /**
     * 批量删除课程模板分类
     * @param ids
     * @return
     */
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
     * 是否显示课程模板分类
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

    /**
     * 获取所有一级课程模板分类
     * @return
     */
    public List<CourseCategory> getAllFirstCategory() {

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setParentId(0);
        courseCategory.setDeleteStatus(2);
        return courseCategoryMapper.select(courseCategory);

    }

    /**
     * 获取一级课程模板分类下的所有二级分类
     * @param id
     * @return
     */
    public List<CourseCategory> getSecondCategoryById(int id) {

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setParentId(id);
        courseCategory.setDeleteStatus(2);
        return courseCategoryMapper.select(courseCategory);

    }

    /**
     * 创建一级课程模板分类
     * @param courseCategory
     * @return
     */
    public int saveFirstCourseCategory(CourseCategory courseCategory) {

        CourseCategory newCourseCategory = new CourseCategory();
        newCourseCategory.setDeleteStatus(2);
        newCourseCategory.setParentId(0);
        //先查询有几个一级分类
        int count = courseCategoryMapper.selectCount(newCourseCategory);

        courseCategory.setDeleteStatus(2);
        courseCategory.setIsFirstLabel(true);
        courseCategory.setDisplayOrder(count + 1);
        return courseCategoryMapper.insertSelective(courseCategory);
    }

}
