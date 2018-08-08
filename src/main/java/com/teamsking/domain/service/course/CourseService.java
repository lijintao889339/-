package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.*;
import com.teamsking.api.dto.sys.SysUserDtoMapper;
import com.teamsking.api.dto.sys.UserDto;
import com.teamsking.domain.entity.course.*;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.repository.*;
import com.teamsking.domain.service.BaseService;

import java.util.List;
import java.util.Map;

import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.open.OpenService;
import com.teamsking.domain.service.open.OpenTeacherService;
import com.teamsking.domain.service.school.SchoolService;
import com.teamsking.domain.service.sys.SysUserService;
import com.teamsking.domain.service.sys.UserCourseService;
import com.teamsking.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class CourseService extends BaseService {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    OpenMapper openMapper;
    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Autowired
    CourseTeacherConnectionMapper courseTeacherConnectionMapper;
    @Autowired
    CourseChapterMapper courseChapterMapper;
    @Autowired
    CourseSectionMapper courseSectionMapper;
    @Autowired
    UserCourseMapper userCourseMapper;
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    CourseItemMapper courseItemMapper;

    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    OpenService openService;
    @Autowired
    OpenTeacherService openTeacherService;
    @Autowired
    CourseTeacherConnectionService courseTeacherConnectionService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    UserCourseService userCourseService;
    @Autowired
    CourseChapterService courseChapterService;

    /**
     * 获取课程模板列表
     *
     * @return
     */
    public Page list(int pageNo, int pageSize) {
        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> teacherIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        Course courseEntity = new Course();
        courseEntity.setDeleteStatus(2);
        List<Course> courseList = courseMapper.select(courseEntity);

        for (Course course : courseList) {
            courseIds.add(course.getId());
        }

        List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionService.getTeacherByCourseIdList(courseIds);
        for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList) {
            teacherIds.add(courseTeacherConnection.getTeacherId());
        }

        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);
        List<Map<String, Object>> openNumList = openMapper.countByCourseIdsGroupByCourseId(courseIds);

        List<CourseListViewDto> courseListViewDtoList = CourseDtoMapper.INSTANCE.entityToListViewDtoList(courseList);

        for (CourseListViewDto course : courseListViewDtoList) {

            List<String> teacherNameList = Lists.newArrayList();
            for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList)
                if (courseTeacherConnection.getCourseId().intValue() == course.getId().intValue()) {
                    for (CourseTeacher courseTeacher : courseTeacherList) {
                        if (courseTeacher.getId().intValue() == courseTeacherConnection.getTeacherId()) {
                            teacherNameList.add(courseTeacher.getTeacherName());
                            course.setTeacherName(teacherNameList);
                            break;
                        }
                    }
                }

            for (Map<String, Object> openNum : openNumList) {
                int courseId = (Integer) openNum.get("courseId");
                if (courseId == course.getId()) {
                    course.setOpenNum(((Long) openNum.get("count")).intValue());
                    break;
                }

            }
        }
        return convertPage((Page) courseList, courseListViewDtoList);
    }


    /**
     * 通过课程名称分页获取课程列表
     * @param pageNo
     * @param pageSize
     * @param courseName
     * @return
     */
    public Page listByReaching(int pageNo, int pageSize, String courseName) {

        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> teacherIds = Lists.newArrayList();

        //分页操作
        PageHelper.startPage(pageNo, pageSize);

        //根据课程名称模糊查询课程信息
        Example courseExample = new Example(Course.class);
        Example.Criteria cri = courseExample.createCriteria();
        courseExample.and().andEqualTo("deleteStatus",2);
        courseExample.and().andLike("courseName","%" + courseName + "%");
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        if (0 != courseList.size()){
            for (Course course : courseList) {
                courseIds.add(course.getId());
            }

            List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionService.getTeacherByCourseIdList(courseIds);
            for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList) {
                teacherIds.add(courseTeacherConnection.getTeacherId());
            }

            List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);
            List<Map<String, Object>> openNumList = openMapper.countByCourseIdsGroupByCourseId(courseIds);

            List<CourseListViewDto> courseListViewDtoList = CourseDtoMapper.INSTANCE.entityToListViewDtoList(courseList);

            for (CourseListViewDto course : courseListViewDtoList) {

                List<String> teacherNameList = Lists.newArrayList();
                for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList)
                    if (courseTeacherConnection.getCourseId().intValue() == course.getId().intValue()) {
                        for (CourseTeacher courseTeacher : courseTeacherList) {
                            if (courseTeacher.getId().intValue() == courseTeacherConnection.getTeacherId()) {
                                teacherNameList.add(courseTeacher.getTeacherName());
                                course.setTeacherName(teacherNameList);
                                break;
                            }
                        }
                    }

                for (Map<String, Object> openNum : openNumList) {
                    int courseId = (Integer) openNum.get("courseId");
                    if (courseId == course.getId()) {
                        course.setOpenNum(((Long) openNum.get("count")).intValue());
                        break;
                    }

                }

            }
            return convertPage((Page) courseList, courseListViewDtoList);
        }else {
            Page p =null;
            return p;
        }

    }

    /**
     * 添加课程模板
     *
     * @param courseInsertDto
     * @return
     */
    public Course save(CourseInsertDto courseInsertDto) {

        //向课程表添加信息
        Course courseEntity = CourseDtoMapper.INSTANCE.insertDtoToEntity(courseInsertDto);
        courseEntity.setCourseStatus("10");
        courseEntity.setDeleteStatus(2);
        int count = courseMapper.insert(courseEntity);

        //将老师Id添加到课程老师关系表
        Integer[] teacherIds = courseInsertDto.getTeacherId();
        List<CourseTeacherConnection> courseTeacherConnectionList = Lists.newArrayList();
        for (Integer teacherId : teacherIds) {
            CourseTeacherConnection courseTeacherConnection = new CourseTeacherConnection();
            courseTeacherConnection.setCourseId(courseEntity.getId());
            courseTeacherConnection.setTeacherId(teacherId);
            courseTeacherConnectionList.add(courseTeacherConnection);
        }
        courseTeacherConnectionMapper.insertConnectionByCourseAndTeachers(courseTeacherConnectionList);

        //将用户Id(导学老师)添加到课程用户关系表
        Integer[] userIds = courseInsertDto.getUserId();
            for (Integer userId : userIds){
                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(userId);
                userCourse.setCourseId(courseEntity.getId());
                userCourseMapper.insertSelective(userCourse);
        }
        return courseEntity;
    }


    /**
     * 通过课程Ids获取课程模板信息
     * @param courseIds
     * @return
     */
    public List<Course> getCourseByCourseIdList(List<Integer> courseIds) {

        Example courseExample = new Example(Course.class);
        Example.Criteria cri = courseExample.createCriteria();
        cri.andIn("id",courseIds);
        return courseMapper.selectByExample(courseExample);

    }



    /**
     * 根据主键修改课程模板状态
     *
     * @param id
     * @return
     */
    public int modifyCourseSatusById(int id) {

        //根据Id查询改课程模板的状态
        Course course = courseMapper.selectByPrimaryKey(id);
        String courseStatus = course.getCourseStatus();

        //课程状态: 10 未发布 20 已发布 30 停用
        if ("30".equals(courseStatus)) {
            courseStatus = "10";

        } else {
            courseStatus = "30";
        }
        course.setCourseStatus(courseStatus);

        return courseMapper.updateByPrimaryKey(course);

    }

    /**
     * 根据主键批量删除课程模板
     *
     * @param ids
     * @return
     */
    public int romoveCourseByIds(Integer[] ids) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids) {
            idList.add(id);
        }

        Course course = new Course();
        course.setDeleteStatus(1);

        Example courseExample = new Example(Course.class);
        Example.Criteria cri = courseExample.createCriteria();
        cri.andIn("id", idList);
        return courseMapper.updateByExampleSelective(course, courseExample);

    }

    /**
     * 根据课程Ids获取课程模板列表
     * @param courseIds
     * @return
     */
    /*public List<Course> getCourseByCourseIdsList(List<Integer> courseIds) {

        Example courseExample = new Example(Course.class);
        Example.Criteria cri = courseExample.createCriteria();
        cri.andIn("id", courseIds);
        return courseMapper.selectByExample(courseExample);

    }*/

    /**
     * 通过一级分类id查询课程模板列表
     * @param id
     * @return
     */
    /*public List<CourseSchoolDto> getCourseListByCategoryId(int id) {
        //获取一级分类下的所有二级分类
        List<Category> categoryList = categoryService.getSecondCategoryById(id);

        List<Integer> categoryIds = Lists.newArrayList();

        for (Category category:categoryList) {
            categoryIds.add(category.getId());

        }

        Example courseExample = new Example(Course.class);
        Example.Criteria cri = courseExample.createCriteria();
        cri.andIn("categoryId", categoryIds);
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        List<CourseSchoolDto> courseSchoolDtoList = CourseDtoMapper.INSTANCE.entityToCourseSchoolDtoList(courseList);

        List<Integer> schoolIds = Lists.newArrayList();
        for (Course course : courseList) {
            schoolIds.add(course.getSchoolId());
        }

        List<School> schoolList = schoolService.getSchoolListByIds(schoolIds);
        for (School school : schoolList){

            for (CourseSchoolDto courseSchoolDto : courseSchoolDtoList) {
                if (courseSchoolDto.getSchoolId().intValue() == school.getId().intValue()){
                    courseSchoolDto.setSchoolName(school.getSchoolName());
                    break;
                }
            }

        }

        return courseSchoolDtoList;
    }*/

    /**
     * 编辑课程前，根据Id获取课程模板及其相关信息
     * @param id
     * @return
     */
    public CourseBeforeEditDto getCourseAndTeacherById(int id) {

        //根据Id查询课程模板信息
        Course course = courseMapper.selectByPrimaryKey(id);
        //数据转换
        CourseBeforeEditDto courseBeforeEditDto = CourseDtoMapper.INSTANCE.entityToBeforeEditDto(course);

        //根据课程Id查询老师信息
        //1.获取与该课程模板有关的老师id
        List<CourseTeacherConnection> courseTeacherConnectionList = courseTeacherConnectionService.getTeacherByCourseId(id);
        List<Integer> teacherIdList = Lists.newArrayList();
        for (CourseTeacherConnection courseTeacherConnection : courseTeacherConnectionList){
            teacherIdList.add(courseTeacherConnection.getTeacherId());
        }

        //2.根据老师IdList获取老师信息
        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIdList);
        List<CourseTeacherNameDto> courseTeacherNameDtoList = CourseTeacherDtoMapper.INSTANCE.entityListToNameListDto(courseTeacherList);
        courseBeforeEditDto.setCourseTeacherList(courseTeacherNameDtoList);

        //根据课程Id查询该课程导学老师信息(用户Id)
        //if (course.getVisibleRange() == 2){
           //获取所有角色为老师的用户
          // List<SysUser> userCourseList = sysUserService.getUserNameByRoleId();
         //  List<UserDto> userDtoListAll = SysUserDtoMapper.INSTANCE.entityDtoToUserDtoList(userCourseList);
          // courseBeforeEditDto.setUserDtoListAll(userDtoListAll);

           //获取该课程模板下的角色为导学老师的用户
           //1.获取与该课程有关的用户Id
           UserCourse newUserCourse = new UserCourse();
           newUserCourse.setCourseId(id);
           List<UserCourse> userCourses = userCourseMapper.select(newUserCourse);

           List<Integer> userIdList = Lists.newArrayList();
           for (UserCourse userCourse : userCourses){
                userIdList.add(userCourse.getUserId());
           }
           //2.根据用户IdList获取老师信息
           List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIdList);
           List<UserDto> userDtoListById = SysUserDtoMapper.INSTANCE.entityDtoToUserDtoList(sysUserList);
           courseBeforeEditDto.setUserDtoListById(userDtoListById);
        //}


        return courseBeforeEditDto;
    }

    /**
     * 编辑课程模板
     * @param courseInsertDto
     * @return
     */
    public Course modify(CourseInsertDto courseInsertDto) {

        //更新课程信息
        Course course = CourseDtoMapper.INSTANCE.insertDtoToEntity(courseInsertDto);
        int count = courseMapper.updateByPrimaryKeySelective(course);

        //更新课程和老师关系
        //1.根据课程id删除之前的老师和课程模板关系记录
        courseTeacherConnectionService.removeConnectionByCourseId(courseInsertDto.getId());
        //2.遍历老师Id,添加新的老师与课程模板的关系
        Integer[] teacherIds = courseInsertDto.getTeacherId();
        List<CourseTeacherConnection> courseTeacherConnectionList = Lists.newArrayList();
        for (Integer teacherId : teacherIds) {

            CourseTeacherConnection connection = new CourseTeacherConnection();
            connection.setCourseId(courseInsertDto.getId());
            connection.setTeacherId(teacherId);
            courseTeacherConnectionList.add(connection);
        }
        courseTeacherConnectionMapper.insertConnectionByCourseAndTeachers(courseTeacherConnectionList);

        //更新课程和用户关系(权限设置：部分可见或全部可见)
        // 先查询之前课程模板与用户有无关系
        /*UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(courseInsertDto.getId());
        List<UserCourse> userCourses = userCourseMapper.select(userCourse);*/

        /**if (courseInsertDto.getVisibleRange() == 2){
            //如果权限设置修改为部分可见
            Integer[] userIds = courseInsertDto.getUserId();
            List<UserCourse> userCourseList = Lists.newArrayList();
            if (null == userCourses){
                //如果之前课程模板与用户无关系，直接添加关系
                for (Integer userId : userIds){
                    UserCourse newUserCourse = new UserCourse();
                    newUserCourse.setCourseId(courseInsertDto.getId());
                    newUserCourse.setUserId(userId);
                    userCourseMapper.insertSelective(newUserCourse);
                }
            }else {
                //如果之前课程模板与用户有关系，先删除之前的关系，再添加关系
                //1.根据课程模板id删除之前的用户和课程模板关系记录
                userCourseService.removeUserCourseByCourseId(courseInsertDto.getId());

                //2.添加更新的用户和课程模板的关系
                for (Integer userId : userIds){
                    UserCourse newUserCourse = new UserCourse();
                    newUserCourse.setCourseId(courseInsertDto.getId());
                    newUserCourse.setUserId(userId);
                    userCourseMapper.insertSelective(newUserCourse);
                }
            }
        }else {
           //如果权限设置修改为全部可见
            if (null != userCourses){
                //如果之前课程模板与用户有关系，删除之前的关系
                userCourseService.removeUserCourseByCourseId(courseInsertDto.getId());
            }
        }**/

        //更新课程和用户关系(设置导学老师)
        Integer[] userIds = courseInsertDto.getUserId();

        //1.根据课程模板id删除之前的用户和课程模板关系记录
        userCourseService.removeUserCourseByCourseId(courseInsertDto.getId());

        //2.添加更新的用户和课程模板的关系
        for (Integer userId : userIds){
            UserCourse newUserCourse = new UserCourse();
            newUserCourse.setCourseId(courseInsertDto.getId());
            newUserCourse.setUserId(userId);
            userCourseMapper.insertSelective(newUserCourse);
        }
        return course;
    }

    /**
     * 添加课程模板下面的章节并且返回
     * @param courseChapterSections
     * @param id
     * @return
     */
    public List<ChapterSectionDto> saveChapterAndSection(CourseChapterSectionDto[] courseChapterSections, int id) {

        for (int i=0; i < courseChapterSections.length; i++){
            //创建课程下的章
            CourseChapter courseChapter = new CourseChapter();
            courseChapter.setDeleteStatus(2);
            courseChapter.setChapterStatus(1);
            courseChapter.setCourseId(id);
            courseChapter.setDisplayOrder(i+1);
            courseChapter.setChapterName(courseChapterSections[i].getLabel());
            courseChapterMapper.insertSelective(courseChapter);

            CourseChapterSecondDto[] chapterSecondDtos = courseChapterSections[i].getChildren();
            for (int j=0; j < chapterSecondDtos.length; j++){
                //创建章下的节
                CourseSection courseSection = new CourseSection();
                courseSection.setDeleteStatus(2);
                courseSection.setCourseId(id);
                courseSection.setChapterId(courseChapter.getId());
                courseSection.setDiaplayOrder(j+1);
                courseSection.setTitle(chapterSecondDtos[j].getLabel());
                courseSectionMapper.insertSelective(courseSection);

            }

        }

        //查询刚刚创建的章和节的信息
        return courseChapterService.list(id);
    }

    /**
     * 根据节Id添加其下的小项内容
     * @param addCourseItemDto
     * @param sectionId
     * @return
     */
    public int saveCourseSectionItems(AddCourseItemDto[] addCourseItemDto, int sectionId) {

        //根据节Id查询节信息
        CourseSection courseSection = courseSectionMapper.selectByPrimaryKey(sectionId);

        int count = 0;
        //将课程模板下的小项内容添加到资源表
        for (int i=0; i<addCourseItemDto.length; i++){
            Node node = new Node();
            node.setDeleteStatus(2);
            node.setNodeType(addCourseItemDto[i].getNodeType());
            node.setFilePath(addCourseItemDto[i].getFilePath());
            node.setTitle(addCourseItemDto[i].getTitle());
            node.setSuffixName(addCourseItemDto[i].getSuffixName());

            nodeMapper.insertSelective(node);

            CourseItem courseItem = new CourseItem();
            courseItem.setCourseId(courseSection.getCourseId());
            courseItem.setChapterId(courseSection.getChapterId());
            courseItem.setSectionId(courseSection.getId());
            courseItem.setDeleteStatus(2);
            courseItem.setRelationId(node.getId());
            courseItem.setItemName(node.getTitle());
            courseItem.setDisplayOrder(i + 1);
            //文档类型
            if (10 == node.getNodeType() || 20 == node.getNodeType() || 30 == node.getNodeType()
                    || 40 == node.getNodeType() || 80 == node.getNodeType()){
                courseItem.setItemType(20);
            }
            //视频类型（包括音频、图片）
            if (50 == node.getNodeType() || 60 == node.getNodeType() || 70 == node.getNodeType()){
                courseItem.setItemType(10);
            }
            count = courseItemMapper.insertSelective(courseItem);

        }

        return count;
    }


}
