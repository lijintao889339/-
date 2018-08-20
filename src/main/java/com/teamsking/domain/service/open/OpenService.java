package com.teamsking.domain.service.open;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.*;
import com.teamsking.api.dto.open.*;
import com.teamsking.api.dto.sys.SysUserDtoMapper;
import com.teamsking.api.dto.sys.UserDto;
import com.teamsking.api.dto.sys.UserTeacherDtoMapper;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.entity.open.*;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.*;

import com.teamsking.domain.service.BaseService;

import java.util.List;
import java.util.Map;

import com.teamsking.domain.service.course.CourseService;
import com.teamsking.domain.service.course.CourseTeacherService;
import com.teamsking.domain.service.node.NodeService;
import com.teamsking.domain.service.school.SchoolService;
import com.teamsking.domain.service.sys.SysUserService;
import com.teamsking.domain.service.sys.UserStudentService;
import com.teamsking.domain.service.sys.UserTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OpenService extends BaseService {

    @Autowired
    OpenMapper openMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    OpenTeacherConnectionMapper openTeacherConnectionMapper;
    @Autowired
    OpenRoleMapper openRoleMapper;
    @Autowired
    CourseTeacherConnectionMapper courseTeacherConnectionMapper;
    @Autowired
    OpenTeacherMapper openTeacherMapper;
    @Autowired
    OpenSetMapper openSetMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;
    @Autowired
    OpenUserTeacherMapper openUserTeacherMapper;
    @Autowired
    OpenGroupMapper openGroupMapper;
    @Autowired
    UserStudentMapper userStudentMapper;


    @Autowired
    SchoolService schoolService;
    @Autowired
    OpenGroupService openGroupService;
    @Autowired
    NodeService nodeService;
    @Autowired
    CourseService courseService;
    @Autowired
    OpenTeacherConnectionService openTeacherConnectionService;
    @Autowired
    OpenTeacherService openTeacherService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    OpenUserService openUserService;
    @Autowired
    UserStudentService userStudentService;
    @Autowired
    UserTeacherService userTeacherService;
    @Autowired
    OpenUserTeacherService openUserTeacherService;


    /**
     * 获取班次管理列表
     * @return
     */
    public Page list(int pageNo, int pageSize){

        List<Integer> openIds = Lists.newArrayList();
        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> schoolIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        Open openEntity = new Open();
        openEntity.setDeleteStatus(2);
        List<Open> openList = openMapper.select(openEntity);
        for (Open open: openList) {
            courseIds.add(open.getCourseId());
            schoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据班次Id查询学生数量
        List<Map<String, Object>> studentNumList = openUserMapper.countByOpenIdsGroupByOpenId(openIds);

        //根据课程Id查询课程名称
        //List<Course> courseList =  courseService.getCourseByCourseIdList(courseIds);

        //根据学校Id查询学校名称
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(schoolIds);

        List<OpenListViewDto> openListViewDtoList = OpenDtoMapper.INSTANCE.entityToListViewDtoList(openList);

        for (OpenListViewDto open: openListViewDtoList) {

            for (Map<String,Object> studentNum : studentNumList) {
                int openId = (Integer) studentNum.get("openId");
                if (openId == open.getId()) {
                    open.setStudentNum(((Long) studentNum.get("count")).intValue());
                    break;
                }

            }

            for (School school : schoolList){
                if (school.getId().intValue() == open.getSchoolId().intValue()){
                    open.setSchoolName(school.getSchoolName());
                    break;
                }
            }

        }

        return convertPage((Page)openList,openListViewDtoList);
    }


    /**
     * 根据班课名称模糊查询班课信息列表
     * @param pageNo
     * @param pageSize
     * @param openName
     * @return
     */
    public Page listByReaching(int pageNo, int pageSize, String openName){

        List<Integer> openIds = Lists.newArrayList();
        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> schoolIds = Lists.newArrayList();

        //分页操作
        PageHelper.startPage(pageNo, pageSize);

        //根据班课名称模糊查询班课信息列表
        Example openExample = new Example(Open.class);
        openExample.and().andEqualTo("deleteStatus",2);
        if ("" != openName){
            openExample.and().andLike("openName","%" + openName + "%");
        }
        //openExample.and().andLike("openName","%" + openName + "%");
        List<Open> openList = openMapper.selectByExample(openExample);

        if (0 != openList.size()){
            for (Open open:openList) {
                openIds.add(open.getId());
                schoolIds.add(open.getSchoolId());
                courseIds.add(open.getCourseId());
            }

            //根据班次Id查询学生数量
            List<Map<String, Object>> studentNumList = openUserMapper.countByOpenIdsGroupByOpenId(openIds);

            //根据课程Id查询课程名称
            //List<Course> courseList =  courseService.getCourseByCourseIdList(courseIds);

            //根据学校Id查询学校名称
            List<School> schoolList = schoolService.getSchoolByShcoolIdList(schoolIds);

            List<OpenListViewDto> openListViewDtoList = OpenDtoMapper.INSTANCE.entityToListViewDtoList(openList);

            for (OpenListViewDto open: openListViewDtoList) {

                for (Map<String,Object> studentNum : studentNumList) {
                    int openId = (Integer) studentNum.get("openId");
                    if (openId == open.getId()) {
                        open.setStudentNum(((Long) studentNum.get("count")).intValue());
                        break;
                    }
                }

                for (School school : schoolList){
                    if (school.getId().intValue() == open.getSchoolId().intValue()){
                        open.setSchoolName(school.getSchoolName());
                        break;
                    }
                }

            }

            return convertPage((Page)openList,openListViewDtoList);

        }else {

            Page page =null;
            return page;
        }

    }




    /**
     * 添加班次管理
     *
     * @param open
     * @return
     */
    /*public int save(Open open) {
        return openMapper.insert(open);
    }*/

    /**
     * 根据id删除班次管理
     *
     * @param id
     * @return
     */
    public int remove(Integer id) {

        Open open = new Open();
        open.setId(id);
        open.setDeleteStatus(1);

        return openMapper.updateByPrimaryKeySelective(open);
    }

    /**
     * 修改班次管理
     *
     * @param open
     * @return
     */
    public int modify(Open open) {
        return openMapper.updateByPrimaryKeySelective(open);
    }

    /**
     * 查询有课程模板创建的班次列表
     *
     * @param pageNo
     * @param pageSize
     * @param courseId
     * @return
     */
    public Page listByCourseId(int pageNo, int pageSize, int courseId) {

        List<Integer> schoolIds = Lists.newArrayList();
        List<Integer> openIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        //根据课程ID查出其下所有班次
        Open openEntity = new Open();
        openEntity.setCourseId(courseId);
        openEntity.setDeleteStatus(2);
        List<Open> openList = openMapper.select(openEntity);

        //遍历班次
        for (Open open : openList) {
            schoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据学校Id查询学校名称
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(schoolIds);

        //根据班次Id查询学生数量
        List<Map<String, Object>> studentNumList = openUserMapper.countByOpenIdsGroupByOpenId(openIds);

        List<OpenListViewDto> openListViewDtoList = OpenDtoMapper.INSTANCE.entityToListViewDtoList(openList);

        for (OpenListViewDto open : openListViewDtoList) {

            for (School school : schoolList) {
                if (school.getId().intValue() == open.getSchoolId().intValue()) {
                    open.setSchoolName(school.getSchoolName());
                    break;
                }
            }

            for (Map<String,Object> studentNum : studentNumList) {
                int openId = (Integer) studentNum.get("openId");
                if (openId == open.getId()) {
                    open.setStudentNum(((Long) studentNum.get("count")).intValue());
                    break;
                }
            }

        }
        return convertPage((Page) openList, openListViewDtoList);

    }

    /**
     * 根据课程主键删除其下面的班次
     * @param ids
     */
    /*public int removeOpenByCouseIds(Integer[] ids) {

        List<Integer> courseIdList = Lists.newArrayList();
        for (Integer courseId: ids) {
            courseIdList.add(courseId);
        }

        Open open = new Open();
        open.setDeleteStatus(1);

        Example openExample = new Example(Open.class);
        Example.Criteria cri = openExample.createCriteria();
        cri.andIn("courseId",courseIdList);
        return openMapper.updateByExampleSelective(open,openExample);
    }*/

    /**
     * 根据课程主键修改课程状态
     * @param id
     * @return
     */
    public int modifyPublishFlagById(int id) {

        Open open = new Open();
        open.setId(id);
        open.setPublishFlag(1);

        return openMapper.updateByPrimaryKeySelective(open);

    }

    /**
     * 班次复制操作
     * @param openCopyDto
     * @return
     */
    public int copyOpen(OpenCopyDto openCopyDto,Integer id){

        Open openEntity = OpenDtoMapper.INSTANCE.insertDtoToEntity(openCopyDto);

        //根据班课Id查询班课信息
        Open open = openMapper.selectByPrimaryKey(id);

        //给复制的班课添加学校Id
        openEntity.setSchoolId(open.getSchoolId());

        openEntity.setOpenCover(open.getOpenCover());

        //添加选课
        openEntity.setOpenMode(open.getOpenMode());
        openEntity.setCourseId(open.getCourseId());
        openEntity.setCourseStatus(open.getCourseStatus());
        openEntity.setDeleteStatus(2);//删除状态：1 已删除 2 未删除
        openEntity.setPublishFlag(2);//发布状态：1 已发布 2 未发布

        return openMapper.insert(openEntity);

    }

    /**
     * 根据分类Id查询班课列表
     * @param categoryId
     * @return
     */
    public List<Open> getOpenListByCategoryId(int categoryId) {

        Open open = new Open();
        open.setCategoryId(categoryId);
        open.setDeleteStatus(2);
        return openMapper.select(open);

    }

    /**
     * 根据分类Ids查询班课列表
     * @param categoryIds
     * @return
     */
    public List<Open> getOPenListByCategoryIdList(List<Integer> categoryIds) {

        Example openExample = new Example(Open.class);
        Example.Criteria cri = openExample.createCriteria();
        cri.andIn("categoryId",categoryIds);
        cri.andEqualTo("deleteStatus",2);
        return openMapper.selectByExample(openExample);

    }


    /**
     * 创建班课
     * @param addOpenDto
     * @return
     */
    public int addOpen(AddOpenDto addOpenDto){

        Open openEntity = OpenDtoMapper.INSTANCE.insertDtoAddToEntity(addOpenDto);

        openEntity.setStudyMode(10);
        openEntity.setDeleteStatus(2);//删除状态：1 已删除 2 未删除
        openEntity.setPublishFlag(2);  //创建的班课默认为未发布
        //添加班课信息
        openMapper.insertSelective(openEntity);
        //将教学老师添加到班课用户关系表
        Integer[] userIds = addOpenDto.getUserId();
        for (Integer teacherId  : userIds){
            //查询到教学老师信息
            UserTeacher userTeacher = userTeacherMapper.selectByPrimaryKey(teacherId);
            //添加教学老师和班课关系
            OpenUserTeacher openUserTeacher = new OpenUserTeacher();
            openUserTeacher.setOpenId(openEntity.getId());
            openUserTeacher.setUserTeacherId(teacherId);
            openUserTeacherMapper.insertSelective(openUserTeacher);

        }

        //将授课教师id添加到班课教师关系表
        Integer[] teacherIds = addOpenDto.getTeacherId();
        List<OpenTeacherConnection> openTeacherConnectionList = Lists.newArrayList();
        for (Integer teacherId : teacherIds) {
            OpenTeacherConnection openTeacherConnection = new OpenTeacherConnection();
            openTeacherConnection.setOpenId(openEntity.getId());
            openTeacherConnection.setTeacherId(teacherId);
            openTeacherConnectionList.add(openTeacherConnection);
        }

        openTeacherConnectionMapper.insertConnectionByOpenAndTeachers(openTeacherConnectionList);

        //添加成绩设置
        if (null != addOpenDto.getOpenSetDto()){
            //给此门课程添加成绩设置
            OpenSet openSet = OpenSetDtoMapper.INSTANCE.dtoToEntity(addOpenDto.getOpenSetDto());
            openSet.setOpenId(openEntity.getId());
            openSet.setSchoolId(openEntity.getSchoolId());
            openSet.setDeleteStatus(2);
            //openSet.setVideoOver(90);  //默认视频观看90%算观看完
            openSetMapper.insertSelective(openSet);
        }

        return openMapper.insertSelective(openEntity);

    }


    /**
     * 根据id查询班课信息
     * @param id
     * @return
     */
//    public Open editOpen(int id){
//        //根据id查询班课信息
//        Open open = openMapper.selectByPrimaryKey(id);
//
//        return open;
//
//
//    }


    /**
     * 根据id查询班课相关信息（编辑）
     * @param id
     * @return
     */
    public EditOpenDto getOpenAndTeacherById(Integer id) {

        //根据班课id查询班课信息
        Open open = openMapper.selectByPrimaryKey(id);

        //数据转换
        EditOpenDto editOpenDtoEntity = OpenDtoMapper.INSTANCE.entityToEditDto(open);

        //根据班课Id查询老师信息
        //1.获取与该班课有关的老师id
        List<OpenTeacherConnection> openTeacherConnectionList = openTeacherConnectionService.getTeacherByOpenId(id);
        List<Integer> teacherIdList = Lists.newArrayList();
        for (OpenTeacherConnection openTeacherConnection : openTeacherConnectionList) {
            teacherIdList.add(openTeacherConnection.getTeacherId());
        }

        //2.根据老师IdList获取老师信息
        List<OpenTeacher> openTeacherList = openTeacherService.getTeacherByTeacherIdList(teacherIdList);
        List<OpenTeacherNameDto> openTeacherNameDtoList = OpenTeacherDtoMapper.INSTANCE.entityListToNameListDto(openTeacherList);
        editOpenDtoEntity.setOpenTeacherNameList(openTeacherNameDtoList);

        //获取该班课下的教学老师
        //1.获取与该班课有关的教学老师Id
        OpenUserTeacher openUserTeacher = new OpenUserTeacher();
        openUserTeacher.setOpenId(id);
        List<OpenUserTeacher> openUserTeacherList = openUserTeacherMapper.select(openUserTeacher);

        List<Integer> userTeacherIdList = Lists.newArrayList();
        for (OpenUserTeacher openTeacher : openUserTeacherList) {
                userTeacherIdList.add(openTeacher.getUserTeacherId());
            }

        //2.获取教学老师信息
        List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIdList);
        List<UserDto> userDtoListById = UserTeacherDtoMapper.INSTANCE.entityDtoToUserDtoList(userTeacherList);
        editOpenDtoEntity.setUserDtoListById(userDtoListById);

        return editOpenDtoEntity;
    }


    /**
     * 根据id编辑保存班课信息
     * @param openEditInsertDto
     * @return
     */
    public Open editPreservationOpen(OpenEditInsertDto openEditInsertDto){

        Open openEntity = OpenDtoMapper.INSTANCE.editAddDtoToEntity(openEditInsertDto);

        openMapper.updateByPrimaryKeySelective(openEntity);

        /*Course course = courseMapper.selectByPrimaryKey(openEntity.getCourseId());
        Integer categoryId = course.getCategoryId();
        openEntity.setCategoryId(categoryId);*/
        //openEntity.setDeleteStatus(2);

        Integer[] teacherIds = openEditInsertDto.getTeacherId();
        List<OpenTeacherConnection> openTeacherConnectionList = Lists.newArrayList();
        for (Integer teacherId : teacherIds) {
            OpenTeacherConnection openTeacherConnection = new OpenTeacherConnection();
            openTeacherConnection.setOpenId(openEntity.getId());
            openTeacherConnection.setTeacherId(teacherId);
            openTeacherConnectionList.add(openTeacherConnection);
        }
        openTeacherConnectionMapper.insertConnectionByOpenAndTeachers(openTeacherConnectionList);

        //更新班课和用户关系(设置教学老师)
        Integer[] userIds = openEditInsertDto.getUserId();

        //1.根据openId删除之前的班课和用户的关系记录
        //openUserService.removeOpenUserByOpenId(openEditInsertDto.getId());
        openUserTeacherService.removeOpenUserTeacherByOpenId(openEditInsertDto.getId());

        //2.添加新的关系
        for (Integer userTeacherId : userIds) {
            /*OpenUser openUser = new OpenUser();
            openUser.setOpenId(openEntity.getId());
            openUser.setUserId(userId);
            openUserMapper.insertSelective(openUser);*/

            OpenUserTeacher openUserTeacher = new OpenUserTeacher();
            openUserTeacher.setOpenId(openEntity.getId());
            openUserTeacher.setUserTeacherId(userTeacherId);
            openUserTeacherMapper.insertSelective(openUserTeacher);
        }


        return openEntity;

    }


    /**
     * 根据id创建教学模式
     * @param addOpenDto
     * @return
     */
    public int addTeachingModel(AddOpenDto addOpenDto){

        Open openEntity = OpenDtoMapper.INSTANCE.insertDtoAddToEntity(addOpenDto);

        return openMapper.updateByPrimaryKeySelective(openEntity);
    }


    /**
     * 根据openIds查询班课信息
     * @param openIds
     * @return
     */
    public List<Open> getOpenByOpenIds(List<Integer> openIds) {

        Example openExample = new Example(Open.class);
        Example.Criteria cri = openExample.createCriteria();
        cri.andIn("id",openIds);
        return openMapper.selectByExample(openExample);
    }

    /**
     * 根据课程模板创建班课前获取班课信息
     * @param courseId
     * @return
     */
    public AddOpenByCourseDto getOpenInfoByCourseId(int courseId) {

        //根据课程模板Id获取课程模板信息
        Course course = courseMapper.selectByPrimaryKey(courseId);

        //根据课程模板Id查询该课程模板和授课老师关系信息
        CourseTeacherConnection connection = new CourseTeacherConnection();
        connection.setCourseId(courseId);
        List<CourseTeacherConnection> connectionList = courseTeacherConnectionMapper.select(connection);

        //遍历集合获取授课老师信息
        List<Integer> teacherIds = Lists.newArrayList();
        for (CourseTeacherConnection courseConnection : connectionList){
            teacherIds.add(courseConnection.getTeacherId());
        }
        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByTeacherIdList(teacherIds);

        //把查到的课程模板授课老师信息添加到班课授课老师表中
        List<OpenTeacher> openTeacherList = CourseTeacherDtoMapper.INSTANCE.entityListToOpenTeacherList(courseTeacherList);
        List<OpenTeacher> openTeachers = Lists.newArrayList();

        for (OpenTeacher openTeacher : openTeacherList){
            openTeacher.setId(null);
            //查询班课老师是否有此条数据
            int count = openTeacherMapper.selectCount(openTeacher);
            if (0 == count){
               openTeacherMapper.insertSelective(openTeacher);
            }
            OpenTeacher openTeacherEntity = openTeacherMapper.selectOne(openTeacher);
            openTeachers.add(openTeacherEntity);

        }

        List<OpenTeacherNameDto> teacherNameDtos = OpenTeacherDtoMapper.INSTANCE.entityListToNameListDto(openTeachers);

        //数据转换
        AddOpenByCourseDto openDto = CourseDtoMapper.INSTANCE.entityToOpenDto(course);

        openDto.setOpenFree(course.getCourseFree());
        openDto.setOpenName(course.getCourseName());
        openDto.setOpenIntroduce(course.getCourseIntroduction());
        openDto.setTeacherNameDtoList(teacherNameDtos);

        return openDto;

    }

    /**
     * 根据课程模板创建班课
     * @param addOpenCourseDto
     * @param courseId
     * @return
     */
    public int saveOpenByCourse(AddOpenCourseDto addOpenCourseDto, int courseId) {

        //根据课程id查询课程模板信息
        Course course = courseMapper.selectByPrimaryKey(courseId);

        //数据转换
        Open open = OpenDtoMapper.INSTANCE.addOpenCourseToEntity(addOpenCourseDto);

        //添加班课基本信息
        open.setCourseId(courseId);
        open.setDeleteStatus(2);
        open.setCourseStatus(course.getCourseStatus());
        open.setPublishFlag(2);  //创建的班课默认为未发布
        int count = openMapper.insertSelective(open);

        //将用户id(教学老师)添加到班课用户关系表
        Integer[] userIds = addOpenCourseDto.getUserId();
        for (Integer teacherId : userIds) {
            //查询到用户信息
            UserTeacher userTeacher = userTeacherMapper.selectByPrimaryKey(teacherId);

            OpenUserTeacher openUserTeacher = new OpenUserTeacher();
            openUserTeacher.setUserTeacherId(teacherId);
            openUserTeacher.setOpenId(open.getId());
            openUserTeacherMapper.insertSelective(openUserTeacher);
        }

        //将授课教师id添加到班课教师关系表
        Integer[] teacherIds = addOpenCourseDto.getTeacherId();
        List<OpenTeacherConnection> openTeacherConnectionList = Lists.newArrayList();
        for (Integer teacherId : teacherIds) {
            OpenTeacherConnection openTeacherConnection = new OpenTeacherConnection();
            openTeacherConnection.setOpenId(open.getId());
            openTeacherConnection.setTeacherId(teacherId);
            openTeacherConnectionList.add(openTeacherConnection);
        }
        openTeacherConnectionMapper.insertConnectionByOpenAndTeachers(openTeacherConnectionList);

        //如果添加成绩设置
        if (null != addOpenCourseDto.getOpenSetDto()){
            //给此门课程添加成绩设置
            OpenSet openSet = OpenSetDtoMapper.INSTANCE.dtoToEntity(addOpenCourseDto.getOpenSetDto());
            openSet.setOpenId(open.getId());
            openSet.setSchoolId(open.getSchoolId());
            openSet.setDeleteStatus(2);
            //openSet.setVideoOver(90);  //默认视频观看90%算观看完
            openSetMapper.insertSelective(openSet);
        }

        return count;
    }

    /**
     * 根据班课Id，获取学生信息
     * @param id
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page getOpenUserById(int id ,int pageNo, int pageSize) {

        //获取该学生与班课信息
        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(2);
        openUser.setOpenId(id);
        List<OpenUser> openUserList = openUserMapper.select(openUser);

        if (0 != openUserList.size()){

            //获取学生ids
            List<Integer> studentIds = Lists.newArrayList();
            for (OpenUser openUserInfo : openUserList){

                studentIds.add(openUserInfo.getUserStudentId());
            }

            PageHelper.startPage(pageNo,pageSize);

            //获取学生信息
            List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(studentIds);

            //获取该学生的用户信息
            List<Integer> userIds = Lists.newArrayList();
            for (UserStudent userStudent : userStudentList){
                userIds.add(userStudent.getUserId());
            }
            List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

            //数据转换
            List<UserStudentDto> userStudentDtos = UserStudentDtoMapper.INSTANCE.entityListToDtoList(userStudentList);

            //遍历集合，获取数据
            for (UserStudentDto userStudentDto : userStudentDtos){

                for (SysUser sysUser : sysUserList){
                    if (userStudentDto.getUserId().intValue() == sysUser.getId().intValue()){
                        userStudentDto.setActivationStatus(sysUser.getActivationStatus());
                        userStudentDto.setMobile(sysUser.getMobile());
                    }
                }
            }
            return convertPage((Page)userStudentList,userStudentDtos);

        }else {
            Page page =null;
            return page;
        }
    }

    /**
     * 通过搜索条件获取学生信息列表
     * @param id
     * @param pageNo
     * @param pageSize
     * @param realName
     * @param studentNo
     * @return
     */
    public Page getOpenUserBySearching(int id, String realName, String studentNo, int pageNo, int pageSize) {

        //获取该学生与班课信息
        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(2);
        openUser.setOpenId(id);
        List<OpenUser> openUserList = openUserMapper.select(openUser);

        if (0 != openUserList.size()){

            //获取学生ids
            List<Integer> studentIds = Lists.newArrayList();
            for (OpenUser openUserInfo : openUserList){
                studentIds.add(openUserInfo.getUserStudentId());
            }

            //分页
            PageHelper.startPage(pageNo,pageSize);

            //获取学生信息
            Example userStudentExample = new Example(UserStudent.class);
            Example.Criteria cri = userStudentExample.createCriteria();
            cri.andIn("id",studentIds);
            if ("" != realName){
                cri.andLike("realName","%" + realName + "%");
            }else if ("" != studentNo){
                cri.andEqualTo("studentNo",studentNo);
            }
            List<UserStudent> userStudentList = userStudentMapper.selectByExample(userStudentExample);

            //获取该学生的用户信息
            List<Integer> userIds = Lists.newArrayList();
            for (UserStudent userStudent : userStudentList){
                userIds.add(userStudent.getUserId());
            }
            List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIds);

            //数据转换
            List<UserStudentDto> userStudentDtos = UserStudentDtoMapper.INSTANCE.entityListToDtoList(userStudentList);

            //遍历集合，获取数据
            for (UserStudentDto userStudentDto : userStudentDtos){

                for (SysUser sysUser : sysUserList){
                    if (userStudentDto.getUserId().intValue() == sysUser.getId().intValue()){
                        userStudentDto.setActivationStatus(sysUser.getActivationStatus());
                        userStudentDto.setMobile(sysUser.getMobile());
                    }
                }
            }
            return convertPage((Page)userStudentList,userStudentDtos);
        }else {
            Page page =null;
            return page;
        }
    }

    /**
     * 通过班课Id获取班课学习权限
     * @param id
     * @return
     */
    public OpenStudyAuthorizeDto getOpenAuthorizeById(int id) {

        Open open = openMapper.selectByPrimaryKey(id);
        OpenStudyAuthorizeDto openStudyAuthorizeDto = OpenDtoMapper.INSTANCE.entityToAuthirizeDto(open);
        return openStudyAuthorizeDto;
    }

    /**
     * 通过班课Id修改班课学习权限
     * @param id
     * @param openStudyAuthorizeDto
     */
    public int deitOpenAuthorizeById(int id, OpenStudyAuthorizeDto openStudyAuthorizeDto) {

        Open open = new Open();
        open.setId(id);
        open.setOpenMode(openStudyAuthorizeDto.getOpenMode());
        open.setDropCourse(openStudyAuthorizeDto.getDropCourse());
        return openMapper.updateByPrimaryKeySelective(open);
    }

    /**
     * 批量删除班课和学生关系
     * @param ids
     * @param openId
     * @return
     */
    public int removeMultiOpenUserByIds(Integer[] ids, int openId) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids){
            idList.add(id);
        }

        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(1);

        Example openUserExample = new Example(OpenUser.class);
        openUserExample.and().andIn("userStudentId",idList);
        openUserExample.and().andEqualTo("openId",openId);
        int count = openUserMapper.updateByExampleSelective(openUser,openUserExample);

        //删除班课下的某一学生后，小组成员应少一人
        for (Integer id : ids){
            //根据关系id，获取班课和学生关系信息
            OpenUser newOpenUser = openUserMapper.selectByPrimaryKey(id);

            //获取该学生小组信息
            if (null != newOpenUser.getGroupId()){
                OpenGroup openGroup = openGroupMapper.selectByPrimaryKey(newOpenUser.getGroupId());
                //将此学生所在小组的成员数量减1
                if(0 != openGroup.getUserCount().intValue()){
                    openGroup.setUserCount(openGroup.getUserCount().intValue() - 1);

                    //更新数据
                    openGroupMapper.updateByPrimaryKeySelective(openGroup);
                }
            }
        }

        return count;
    }


}
