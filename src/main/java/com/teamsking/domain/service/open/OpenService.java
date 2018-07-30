package com.teamsking.domain.service.open;


import afu.org.checkerframework.checker.oigj.qual.O;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.*;
import com.teamsking.api.dto.school.SchoolDtoMapper;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.open.OpenTeacherConnection;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.repository.*;

import com.teamsking.domain.service.BaseService;

import java.util.List;
import java.util.Map;

import com.teamsking.domain.service.course.CourseService;
import com.teamsking.domain.service.node.NodeService;
import com.teamsking.domain.service.school.SchoolService;
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
    SchoolService schoolService;
    @Autowired
    OpenGroupService openGroupService;
    @Autowired
    NodeService nodeService;
    @Autowired
    CourseService courseService;

    /**
     * 获取班次管理列表
     * @return
     */
    public Page list(int pageNo, int pageSize){

        List<Integer> openIds = Lists.newArrayList();
        List<Integer> courseIds = Lists.newArrayList();
        List<Integer> shcoolIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        Open openEntity = new Open();
        openEntity.setDeleteStatus(2);
        List<Open> openList = openMapper.select(openEntity);
        for (Open open: openList) {
            courseIds.add(open.getCourseId());
            shcoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据班次Id查询学生数量
        List<Map<String, Object>> studentNumList = openUserMapper.countByOpenIdsGroupByOpenId(openIds);

        //根据课程Id查询课程名称
        List<Course> courseList =  courseService.getCourseByCourseIdList(courseIds);

        //根据学校Id查询学校名称
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(shcoolIds);

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
     * 添加班次管理
     *
     * @param open
     * @return
     */
    public int save(Open open) {
        return openMapper.insert(open);
    }

    /**
     * 删除班次管理
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
     * 查询课程下面的班次列表
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
    public int removeOpenByCouseIds(Integer[] ids) {

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
    }

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
        return openMapper.selectByExample(openExample);

    }


    /**
     * 创建班课
     * @param addOpenDto
     * @return
     */
//    public int addOpen(AddOpenDto addOpenDto){
//
//        Open openEntity = OpenDtoMapper.INSTANCE.insertDtoAddToEntity(addOpenDto);
//
//        //根据课程id获取该课程的二级分类id
//        Course course = courseMapper.selectByPrimaryKey(openEntity.getCourseId());
//        Integer categoryId = course.getCategoryId();
//        //设置添加的班课的二级分类Id
//        openEntity.setCategoryId(categoryId);
//
//        openEntity.setDeleteStatus(2);//删除状态：1 已删除 2 未删除
//
//        return openMapper.insertSelective(openEntity);
//
//    }


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
     * 根据id编辑保存班课信息
     * @param editOpenDto
     * @return
     */
//    public int editPreservationOpen(EditOpenDto editOpenDto){
//
//        Open openEntity = OpenDtoMapper.INSTANCE.editInsertDtoToEntity(editOpenDto);
//
//        Course course = courseMapper.selectByPrimaryKey(openEntity.getCourseId());
//        Integer categoryId = course.getCategoryId();
//        openEntity.setCategoryId(categoryId);
//        //openEntity.setDeleteStatus(2);
//
//        Integer[] teacherIds = editOpenDto.getTeacherId();
//        List<OpenTeacherConnection> openTeacherConnectionList = Lists.newArrayList();
//        for (Integer teacherId : teacherIds) {
//            OpenTeacherConnection openTeacherConnection = new OpenTeacherConnection();
//            openTeacherConnection.setOpenId(openEntity.getId());
//            openTeacherConnection.setTeacherId(teacherId);
//            openTeacherConnectionList.add(openTeacherConnection);
//        }
//        openTeacherConnectionMapper.insertConnectionByOpenAndTeachers(openTeacherConnectionList);
//
//        //return openMapper.updateByPrimaryKeySelective(openEntity);
//        return openMapper.updateByPrimaryKeySelective(openEntity);
//
//    }

}
