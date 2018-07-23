package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import com.teamsking.api.dto.course.CourseEvaluateDto;
import com.teamsking.api.dto.course.CourseEvaluateDtoMapper;
import com.teamsking.api.dto.course.CourseEvaluateInfoDto;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseEvaluate;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.repository.*;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

@Slf4j
@Service
public class CourseEvaluateService extends BaseService {


    @Autowired
    CourseEvaluateMapper courseEvaluateMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseTeacherConnectionMapper courseTeacherConnectionMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CourseTeacherConnectionService courseTeacherConnectionService;
    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    CourseService courseService;
    @Autowired
    CategoryService categoryService;


    public Page list(int pageNo, int pageSize){

        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> userIds = Lists.newArrayList();
        List<Integer> teacherIds = Lists.newArrayList();
        List<Integer> categoryIds = Lists.newArrayList();
        List<Integer> parentIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        CourseEvaluate courseEvaluateEntity = new CourseEvaluate();
        courseEvaluateEntity.setDeleteStatus(2);
        List<CourseEvaluate> courseEvaluateList = courseEvaluateMapper.select(courseEvaluateEntity);

        for (CourseEvaluate courseEvaluate : courseEvaluateList) {
            courseIds.add(courseEvaluate.getCourseId());
            userIds.add(courseEvaluate.getUserId());
            categoryIds.add(courseEvaluate.getCategoryId());
        }

        List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

        List<Course> courseList = courseService.getCourseByCourseIdsList(courseIds);

        List<Category> categoryList = categoryService.getCategoryByCategoryId(categoryIds);
        for (Category category:categoryList) {
            parentIds.add(category.getParentId());
        }

        List<Category> categoryParentList = categoryService.getCategoryByCategoryId(parentIds);

        List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionService.getTeacherByCourseIdList(courseIds);
        for (CourseTeacherConnection courseTeacherConnection: courseTeacherConnectionList) {
            teacherIds.add(courseTeacherConnection.getTeacherId());
        }

        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);

        List<CourseEvaluateDto> courseEvaluateDtoList = CourseEvaluateDtoMapper.INSTANCE.entityListToDtoList(courseEvaluateList);

        for (CourseEvaluateDto courseEvaluate : courseEvaluateDtoList) {

            for (SysUser sysUser:sysUserList) {
                if (sysUser.getId().intValue() == courseEvaluate.getUserId()){
                    courseEvaluate.setUserName(sysUser.getUserName());
                    courseEvaluate.setAvatar(sysUser.getAvatar());
                    break;
                }
            }

            for (Course course:courseList) {
                if (course.getId().intValue() == courseEvaluate.getCourseId()) {
                    courseEvaluate.setCourseName(course.getCourseName());
                    break;
                }
            }

            for (Category category : categoryList){
                if (category.getId().intValue() == courseEvaluate.getCategoryId()){
                   for (Category parentCategory : categoryParentList){
                       if (parentCategory.getId().intValue() == category.getParentId()){
                           courseEvaluate.setCategoryName(parentCategory.getCategoryName());
                           break;
                       }
                   }
                }
            }

            List<String> teacherNameList = Lists.newArrayList();
            for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList) {
                if (courseTeacherConnection.getCourseId().intValue() == courseEvaluate.getCourseId().intValue()) {
                    for (CourseTeacher courseTeacher : courseTeacherList) {
                        if (courseTeacher.getId().intValue() == courseTeacherConnection.getTeacherId()) {
                            teacherNameList.add(courseTeacher.getTeacherName());
                            courseEvaluate.setTeacherName(teacherNameList);
                            break;
                        }
                    }
                }
            }

        }
        return convertPage((Page)courseEvaluateList,courseEvaluateDtoList);
    }

    /**
     * 根据Id获取课程评价详情
     * @param id
     * @return
     */
//    public CourseEvaluateInfoDto getCourseEvaluteInfoById( int id) {
//
//
//        //根据id查询课程评价
//        CourseEvaluate courseEvaluate = courseEvaluateMapper.selectByPrimaryKey(id);
//
//        //数据转换
//        CourseEvaluateInfoDto courseEvaluateInfoDto = CourseEvaluateDtoMapper.INSTANCE.entityToInfoDto(courseEvaluate);
//
//        //根据用户id查询用户相关内容
//        Integer userId = courseEvaluate.getUserId();
//        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
//        courseEvaluateInfoDto.setAvatar(sysUser.getAvatar());
//        courseEvaluateInfoDto.setUserName(sysUser.getUserName());
//
//        //根据课程Id查询课程课程名称
//        Integer courseId = courseEvaluate.getCourseId();
//        Course course = courseMapper.selectByPrimaryKey(courseId);
//        courseEvaluateInfoDto.setCourseName(course.getCourseName());
//
//        //根据课程Id查询课程老师关系列表
//        CourseTeacherConnection courseTeacherConnection = new CourseTeacherConnection();
//        courseTeacherConnection.setCourseId(courseId);
//        List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionMapper.select(courseTeacherConnection);
//        //遍历集合，获取老师Id列表
//        List<Integer> teacherIds = Lists.newArrayList();
//        for (CourseTeacherConnection courseTeacher: courseTeacherConnectionList) {
//            teacherIds.add(courseTeacher.getTeacherId());
//        }
//        //通过老师Id列表获取老师列表
//        List<CourseTeacher> teacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);
//        //遍历集合，获取老师名称列表
//        List<String> teacherNameList = Lists.newArrayList();
//        for (CourseTeacher teacher:teacherList) {
//            teacherNameList.add(teacher.getTeacherName());
//        }
//        //获取课程Id所对应的授课老师列表
//        courseEvaluateInfoDto.setTeacherName(teacherNameList);
//
//        //根据分类Id获取课程对应的二级分类Id
//        Integer categoryId = courseEvaluate.getCategoryId();
//        Category category = categoryMapper.selectByPrimaryKey(categoryId);
//        //获取二级分类Id的父级Id
//        Integer parentId = category.getParentId();
//        Category categoryEntity = categoryMapper.selectByPrimaryKey(parentId);
//        //获取课程对应的一级分类名称
//        courseEvaluateInfoDto.setCategoryName(categoryEntity.getCategoryName());
//
//        return courseEvaluateInfoDto;
//    }

    /**
     * 根据IDS批量删除课程评价
     * @param ids
     * @return
     */
    public int removeCourseEvaluateByIds(Integer[] ids) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids) {
            idList.add(id);
        }

        CourseEvaluate courseEvaluate = new CourseEvaluate();
        courseEvaluate.setDeleteStatus(1);

        Example courseEvaluateExample = new Example(Course.class);
        Example.Criteria cri = courseEvaluateExample.createCriteria();
        cri.andIn("id", idList);
        return courseEvaluateMapper.updateByExampleSelective(courseEvaluate, courseEvaluateExample);
    }
}
