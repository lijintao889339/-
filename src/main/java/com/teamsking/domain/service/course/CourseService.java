package com.teamsking.domain.service.course;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.*;
import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.repository.CourseMapper;
import com.teamsking.domain.repository.CourseTeacherMapper;
import com.teamsking.domain.repository.OpenMapper;
import com.teamsking.domain.service.BaseService;

import java.util.List;
import java.util.Map;

import com.teamsking.domain.service.category.CategoryService;
import com.teamsking.domain.service.open.OpenService;
import com.teamsking.domain.service.school.SchoolService;
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
    CourseTeacherMapper courseTeacherMapper;

    @Autowired
    CourseTeacherService courseTeacherService;

    @Autowired
    OpenService openService;

    @Autowired
    CourseTeacherConnectionService courseTeacherConnectionService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    SchoolService schoolService;

    /**
     * 获取课程列表
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
     * 添加课程
     *
     * @param courseInsertDto
     * @return
     */
    public int save(CourseInsertDto courseInsertDto) {

        Course courseEntity = CourseDtoMapper.INSTANCE.insertDtoToEntity(courseInsertDto);
        courseEntity.setCourseStatus("10");
        courseEntity.setDeleteStatus(2);
        int count = courseMapper.insert(courseEntity);


        if (count > 0) {
            CourseTeacher courseTeacher = CourseTeacherDtoMapper.INSTANCE.insertDtoToEntity(courseInsertDto);
            courseTeacherMapper.insertSelective(courseTeacher);
        }

        return count;
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
     *
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

    /**
     * 根据主键修改课程状态
     *
     * @param id
     * @return
     */
    public int modifyCourseSatusById(int id) {

        //根据Id查询改课程的状态
        Course course = courseMapper.selectByPrimaryKey(id);
        String courseStatus = course.getCourseStatus();

        if ("30".equals(courseStatus)) {
            courseStatus = "10";

        } else {
            courseStatus = "30";
        }

        Course courseEntity = new Course();
        course.setCourseStatus(courseStatus);

        return courseMapper.updateByPrimaryKey(course);

    }

    /**
     * 根据主键批量删除课程(及其下面的班次)
     *
     * @param ids
     * @return
     */
    public int romoveCourseByIds(Integer[] ids) {

        openService.removeOpenByCouseIds(ids);

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


    public List<Course> getCourseByCourseIdsList(List<Integer> courseIds) {

        Example courseExample = new Example(Course.class);
        return courseMapper.selectByExample(courseExample);

    }

    /**
     * 通过一级分类id查询课程列表
     * @param id
     * @return
     */
    public List<CourseSchoolDto> getCourseListByCategoryId(int id) {
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
    }
}
