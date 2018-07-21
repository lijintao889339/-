package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import com.teamsking.api.dto.course.CourseEvaluateDto;
import com.teamsking.api.dto.course.CourseEvaluateDtoMapper;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseEvaluate;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.repository.CourseEvaluateMapper;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class CourseEvaluateService extends BaseService {


    @Autowired
    CourseEvaluateMapper courseEvaluateMapper;

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
        List<CourseEvaluateDto> resultList = Lists.newArrayList();
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

        for (CourseEvaluate courseEvaluate : courseEvaluateList) {
            CourseEvaluateDto courseEvaluateDto = CourseEvaluateDtoMapper.INSTANCE.entityToDto(courseEvaluate);

            for (SysUser sysUser:sysUserList) {
                if (sysUser.getId().intValue() == courseEvaluate.getUserId()){
                    courseEvaluateDto.setUserName(sysUser.getUserName());
                    break;
                }
            }

            for (Course course:courseList) {
                if (course.getId().intValue() == courseEvaluate.getCourseId()) {
                    courseEvaluateDto.setCourseName(course.getCourseName());
                    break;
                }
            }

            for (Category category : categoryList){
                if (category.getId().intValue() == courseEvaluate.getCategoryId()){
                   for (Category parentCategory : categoryParentList){
                       if (parentCategory.getId().intValue() == category.getParentId()){
                           courseEvaluateDto.setCategoryName(parentCategory.getCategoryName());
                           break;
                       }
                   }
                }
            }

            for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList) {
                if (courseTeacherConnection.getCourseId().intValue() == courseEvaluate.getCourseId().intValue()) {
                    for (CourseTeacher courseTeacher : courseTeacherList) {
                        if (courseTeacher.getId().intValue() == courseTeacherConnection.getTeacherId()) {
                            courseEvaluateDto.setTeacherName(courseTeacher.getTeacherName());
                            break;
                        }
                    }
                }
            }
            resultList.add(courseEvaluateDto);

        }
        return convertPage((Page)courseEvaluateList,resultList);
    }


}
