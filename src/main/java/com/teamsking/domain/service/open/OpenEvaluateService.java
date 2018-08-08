package com.teamsking.domain.service.open;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import com.teamsking.api.dto.open.OpenEvaluateDto;
import com.teamsking.api.dto.open.OpenEvaluateDtoMapper;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.open.OpenEvaluate;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.entity.open.OpenTeacher;
import com.teamsking.domain.entity.open.OpenTeacherConnection;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.repository.*;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class OpenEvaluateService extends BaseService {


    @Autowired
    OpenEvaluateMapper openEvaluateMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    SysUserService sysUserService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OpenService openService;
    @Autowired
    OpenTeacherConnectionService openTeacherConnectionService;
    @Autowired
    OpenTeacherService openTeacherService;

    /**
     * 分页获取班课评价
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page list(int pageNo, int pageSize){

        List<Integer> openIds = Lists.newArrayList();
        List<Integer> userIds = Lists.newArrayList();
        List<Integer> teacherIds = Lists.newArrayList();
        List<Integer> categoryIds = Lists.newArrayList();

        //分页(对下面第一个查出结果分页)
        PageHelper.startPage(pageNo, pageSize);

        //查询所有的班课评价
        OpenEvaluate openEvaluateEntity = new OpenEvaluate();
        openEvaluateEntity.setDeleteStatus(2);
        List<OpenEvaluate> openEvaluateList = openEvaluateMapper.select(openEvaluateEntity);

        //遍历集合(获取班课ids,用户ids，一级分类ids)
        for (OpenEvaluate openEvaluate : openEvaluateList) {
            openIds.add(openEvaluate.getOpenId());
            userIds.add(openEvaluate.getUserId());
            categoryIds.add(openEvaluate.getFirstCategoryId());
        }

        //查询用户信息
        List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

        //查询班课信息
        List<Open> openList = openService.getOpenByOpenIds(openIds);

        //查询分类信息(一级分类)
        List<Category> categoryList = categoryService.getCategoryByCategoryId(categoryIds);

        /*for (Category category:categoryList) {
            parentIds.add(category.getParentId());
        }
        //查询分类信息(一级分类)
        List<Category> categoryParentList = categoryService.getCategoryByCategoryId(parentIds);*/

        //查询授课老师和班课关系(获取老师Ids)
        List<OpenTeacherConnection> connectionList = openTeacherConnectionService.getTeacherByOpenIds(openIds);
        for (OpenTeacherConnection connection: connectionList) {
            teacherIds.add(connection.getTeacherId());
        }
        //查询授课老师信息
        List<OpenTeacher> openTeacherList = openTeacherService.getTeacherByTeacherIdList(teacherIds);

        /*List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionService.getTeacherByCourseIdList(courseIds);
        for (CourseTeacherConnection courseTeacherConnection: courseTeacherConnectionList) {
            teacherIds.add(courseTeacherConnection.getTeacherId());
        }
        //查询授课老师信息
        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);*/

        //数据转换
        List<OpenEvaluateDto> openEvaluateDtos = OpenEvaluateDtoMapper.INSTANCE.entityListToDtoList(openEvaluateList);

        //遍历集合
        for (OpenEvaluateDto openEvaluate : openEvaluateDtos) {

            //遍历集合，获取用户信息
            for (SysUser sysUser:sysUserList) {
                if (sysUser.getId().intValue() == openEvaluate.getUserId()){
                    openEvaluate.setUserName(sysUser.getUserName());
                    openEvaluate.setAvatar(sysUser.getAvatar());
                    openEvaluate.setStudentNo(sysUser.getStudentNo());
                    break;
                }
            }

            //遍历集合，获取班课信息
            for (Open open:openList) {
                if (open.getId().intValue() == openEvaluate.getOpenId()) {
                    openEvaluate.setOpenName(open.getOpenName());
                    break;
                }
            }

            //遍历集合，获取课程分类模板信息
            for (Category category : categoryList){
                if (category.getId().intValue() == openEvaluate.getFirstCategoryId()){
                   //for (Category parentCategory : categoryParentList){
                      // if (parentCategory.getId().intValue() == category.getParentId()){
                    openEvaluate.setCategoryName(category.getLabel());
                           break;
                      // }
                   //}
                }
            }

            //遍历集合，获取授课老师信息
            List<String> teacherNameList = Lists.newArrayList();
            for (OpenTeacherConnection connection : connectionList) {
                if (connection.getOpenId().intValue() == openEvaluate.getOpenId().intValue()) {
                    for (OpenTeacher openTeacher : openTeacherList) {
                        if (openTeacher.getId().intValue() == connection.getTeacherId()) {
                            teacherNameList.add(openTeacher.getTeacherName());
                            openEvaluate.setTeacherName(teacherNameList);
                            break;
                        }
                    }
                }
            }

        }
        return convertPage((Page)openEvaluateList,openEvaluateDtos);
    }

    /**
     * 根据Id获取课程评价详情
     * @param id
     * @return
     */
//    public OpenEvaluateInfoDto getCourseEvaluteInfoById( int id) {
//
//
//        //根据id查询课程评价
//        OpenEvaluate courseEvaluate = courseEvaluateMapper.selectByPrimaryKey(id);
//
//        //数据转换
//        OpenEvaluateInfoDto courseEvaluateInfoDto = OpenEvaluateDtoMapper.INSTANCE.entityToInfoDto(courseEvaluate);
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
     * 根据IDS批量删除课程模板评价
     * @param ids
     * @return
     */
    /*public int removeCourseEvaluateByIds(Integer[] ids) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids) {
            idList.add(id);
        }

        OpenEvaluate courseEvaluate = new OpenEvaluate();
        courseEvaluate.setDeleteStatus(1);

        Example courseEvaluateExample = new Example(Course.class);
        Example.Criteria cri = courseEvaluateExample.createCriteria();
        cri.andIn("id", idList);
        return courseEvaluateMapper.updateByExampleSelective(courseEvaluate, courseEvaluateExample);
    }*/

    /**
     * 判断是否可以显示课程模板评价
     * @param courseEvaluate
     * @return
     */
    /*public int isShowById(OpenEvaluate courseEvaluate){

        if (true == courseEvaluate.getIsShow()){
            courseEvaluate.setIsShow(true);
        }else {
            courseEvaluate.setIsShow(false);
        }
        return courseEvaluateMapper.updateByPrimaryKeySelective(courseEvaluate);
    }
*/

    /**
     * 通过条件搜索课程模板评价
     * @param pageNo
     * @param pageSize
     * @param courseId
     * @param categoryId
     * @param teacherId
     * @return
     */
    /*public List listBySearching(int pageNo, int pageSize, Integer courseId, Integer categoryId, Integer teacherId) {

        if (null != courseId && null != categoryId && null != teacherId){
            //根据老师Id查询课程模板与老师的关系数据
            CourseTeacherConnection connection = new CourseTeacherConnection();
            connection.setTeacherId(teacherId);
            List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionMapper.select(connection);

            List<Integer> courseIds = Lists.newArrayList();
            for (CourseTeacherConnection newConnection : courseTeacherConnectionList){
                courseIds.add(newConnection.getCourseId());
            }

            //查询搜索的课程模板评价
            *//*OpenEvaluate courseEvaluateEntity = new OpenEvaluate();
            courseEvaluateEntity.setDeleteStatus(2);
            courseEvaluateEntity.setCourseId(courseId);
            courseEvaluateEntity.setCategoryId(categoryId);
            List<OpenEvaluate> courseEvaluateList = courseEvaluateMapper.select(courseEvaluateEntity);*//*

            Example evaluate

        }else {

        }

    }*/
}
