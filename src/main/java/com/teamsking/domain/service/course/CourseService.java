package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.CourseDtoMapper;
import com.teamsking.api.dto.course.CourseListViewDto;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.repository.CourseMapper;
import com.teamsking.domain.repository.OpenMapper;
import com.teamsking.domain.service.BaseService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class CourseService extends BaseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    OpenMapper openMapper;

    @Autowired
    CourseTeacherService courseTeacherService;

    /**
     * 获取课程列表
     *
     * @return
     */
    public Page list(int pageNo, int pageSize) {
        List<Integer> courseIds = Lists.newArrayList();
        List<CourseListViewDto> resultList = Lists.newArrayList();

        PageHelper.startPage(pageNo, pageSize);
        List<Course> courseList = courseMapper.selectAll();

        for (Course course : courseList) {
            courseIds.add(course.getId());
        }

        List<CourseTeacher> courseTeacherList = courseTeacherService.getTeacherByCourseIdList(courseIds);
        List<Map<String, Object>> openNumList = openMapper.countByCourseIdsGroupByCourseId(courseIds);

        for (Course course : courseList) {
            CourseListViewDto courseListViewDto = CourseDtoMapper.INSTANCE.entityToListViewDto(course);
            for (CourseTeacher courseTeacher : courseTeacherList) {
                if (courseTeacher.getCourseId().intValue() == course.getId().intValue()) {
                    courseListViewDto.setTeacherName(courseTeacher.getTeacherName());
                    break;
                }
            }
            for (Map<String, Object> openNum : openNumList) {
                int courseId = (Integer) openNum.get("courseId");
                if (courseId == course.getId()) {
                    courseListViewDto.setOpenNum(((Long) openNum.get("count")).intValue());
                    break;
                }
            }
            resultList.add(courseListViewDto);
        }
        return convertPage((Page)courseList,resultList);
    }

    /**
     * 添加课程
     *
     * @param course
     * @return
     */
    public int save(Course course) {
        return courseMapper.insert(course);
    }

    /**
     * 删除课程
     *
     * @param id
     * @return
     */
    public int remove(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程
     *
     * @param course
     * @return
     */
    public int modify(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    /**
     * 根据课程ID删除课程及其下面的班次
     * @param id
     */
    public int remove1(int id) {

        Open open = new Open();
        open.setCourseId(id);
        int deleteOpenCount = openMapper.delete(open);

        int count = courseMapper.deleteByPrimaryKey(id);
        return count;
    }

    public List<Course> getCourseByCourseIdList(List<Integer> courseIds) {

        Example courseExample = new Example(Course.class);
        return courseMapper.selectByExample(courseExample);

    }

}
