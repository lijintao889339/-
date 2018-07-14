package com.teamsking.domain.service.open;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenListViewDto;
import com.teamsking.api.dto.open.OpenDtoMapper;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.open.OpenGroup;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.repository.CourseMapper;
import com.teamsking.domain.repository.OpenMapper;

import com.teamsking.domain.service.BaseService;

import java.util.List;

import com.teamsking.domain.service.course.CourseService;
import com.teamsking.domain.service.node.NodeService;
import com.teamsking.domain.service.school.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenService extends BaseService {

    @Autowired
    OpenMapper openMapper;
    @Autowired
    CourseMapper courseMapper;

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
        List<OpenListViewDto> resultList = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        List<Open> openList = openMapper.selectAll();
        for (Open open: openList) {
            courseIds.add(open.getCourseId());
            shcoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据班次Id查询学生数量
        List<OpenGroup> openGroupList = openGroupService.getOpenGroupByOpenIdList(openIds);

        //根据班次Id查询班次封面地址
        List<Node> nodeList = nodeService.getNodeByOpenIdList(openIds);

        //根据课程Id查询课程名称
        List<Course> courseList =  courseService.getCourseByCourseIdList(courseIds);

        //根据学校Id查询学校名称
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(shcoolIds);

        for (Open open: openList) {
            OpenListViewDto oPenListViewDto = OpenDtoMapper.INSTANCE.entityToListViewDto(open);

            for (OpenGroup openGroup : openGroupList) {
                if (openGroup.getOpenId().intValue() == open.getId().intValue()){
                    oPenListViewDto.setUserCount(openGroup.getUserCount());
                    break;
                }
            }

            for (Node node : nodeList){
                if (node.getOpenId().intValue() == open.getId().intValue()){
                    oPenListViewDto.setCoverPath(node.getCoverPath());
                    break;
                }
            }

            for (Course course : courseList){
                if (course.getId().intValue() == open.getCourseId().intValue()){
                    oPenListViewDto.setCourseName(course.getCourseName());
                    oPenListViewDto.setCourseSell(course.getCourseSell());
                    break;
                }
            }

            for (School school : schoolList){
                if (school.getId().intValue() == open.getSchoolId().intValue()){
                    oPenListViewDto.setSchoolName(school.getSchoolName());
                    break;
                }
            }

            resultList.add(oPenListViewDto);
        }


        return convertPage((Page)openList,resultList);
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
        return openMapper.deleteByPrimaryKey(id);
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

        List<Integer> shcoolIds = Lists.newArrayList();
        List<OpenListViewDto> resultList = Lists.newArrayList();
        List<Integer> openIds = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);

        //根据课程ID查出其下所有班次
        Open Open = new Open();
        Open.setCourseId(courseId);
        List<Open> openList = openMapper.select(Open);

        //遍历班次
        for (Open open : openList) {
            shcoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据学校Id查询学校名称
        List<School> schoolList = schoolService.getSchoolByShcoolIdList(shcoolIds);

        //根据班次Id查询学生数量
        List<OpenGroup> openGroupList = openGroupService.getOpenGroupByOpenIdList(openIds);

        //根据班次Id查询班次封面地址
        List<Node> nodeList = nodeService.getNodeByOpenIdList(openIds);

        //根据课程ID查询课程名称
        Course course = new Course();
        course.setId(courseId);
        Course courseOne = courseMapper.selectOne(course);

        for (Open open : openList) {
            OpenListViewDto oPenListViewDto = OpenDtoMapper.INSTANCE.entityToListViewDto(open);

            for (School school : schoolList) {
                if (school.getId().intValue() == open.getSchoolId().intValue()) {
                    oPenListViewDto.setSchoolName(school.getSchoolName());
                    break;
                }
            }

            for (OpenGroup openGroup : openGroupList) {
                if (openGroup.getOpenId().intValue() == open.getId().intValue()) {
                    oPenListViewDto.setUserCount(openGroup.getUserCount());
                    break;
                }
            }

            for (Node node : nodeList) {
                if (node.getOpenId().intValue() == open.getId().intValue()) {
                    oPenListViewDto.setCoverPath(node.getCoverPath());
                    break;
                }
            }

            oPenListViewDto.setCourseName(courseOne.getCourseName());
            oPenListViewDto.setCourseSell(course.getCourseSell());
            resultList.add(oPenListViewDto);
        }

        return convertPage((Page) openList, resultList);

    }

}
