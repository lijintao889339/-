package com.teamsking.domain.service.open;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenCopyDto;
import com.teamsking.api.dto.open.OpenListViewDto;
import com.teamsking.api.dto.open.OpenDtoMapper;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.repository.CourseMapper;
import com.teamsking.domain.repository.OpenMapper;

import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.SchoolMapper;
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

        List<Open> openList = openMapper.selectAll();
        for (Open open: openList) {
            courseIds.add(open.getCourseId());
            shcoolIds.add(open.getSchoolId());
            openIds.add(open.getId());
        }

        //根据班次Id查询学生数量
        List<Map<String, Object>> studentNumList = openUserMapper.countByOpenIdsGroupByOpenId(openIds);

        //根据班次Id查询班次封面地址
        List<Node> nodeList = nodeService.getNodeByOpenIdList(openIds);

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

            for (Node node : nodeList){
                if (node.getOpenId().intValue() == open.getId().intValue()){
                    open.setCoverPath(node.getCoverPath());
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

        //根据班次Id查询班次封面地址
        List<Node> nodeList = nodeService.getNodeByOpenIdList(openIds);

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

            for (Node node : nodeList) {
                if (node.getOpenId().intValue() == open.getId().intValue()) {
                    open.setCoverPath(node.getCoverPath());
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
    public int copyOpen(OpenCopyDto openCopyDto){

        Open openEntity = OpenDtoMapper.INSTANCE.insertDtoToEntity(openCopyDto);

        //根据班课Id查询班课信息
        Integer openId = openCopyDto.getId();
        Open open = openMapper.selectByPrimaryKey(openId);

        //给复制的班课添加学校Id
        openEntity.setSchoolId(open.getSchoolId());

        //添加选课
        openEntity.setOpenMode(open.getOpenMode());
        openEntity.setDeleteStatus(2);//删除状态：1 已删除 2 未删除
        openEntity.setPublishFlag(2);//发布状态：1 已发布 2 未发布

        return openMapper.insert(openEntity);

    }
}
