package com.teamsking.domain.service.course;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.CourseTeacherDto;
import com.teamsking.api.dto.course.CourseTeacherDtoMapper;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.course.CourseTeacherTag;
import com.teamsking.domain.entity.tag.Tag;
import com.teamsking.domain.repository.CourseTeacherMapper;
import java.util.List;

import com.teamsking.domain.repository.CourseTeacherTagMapper;
import com.teamsking.domain.repository.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    CourseTeacherTagMapper courseTeacherTagMapper;

    /**
     * 获取课程-教师列表
     *
     * @return
     */
    public List<CourseTeacher> list() {
        return courseTeacherMapper.selectAll();
    }

    /**
     * 添加课程-教师
     *
     * @param courseTeacherDto
     * @return
     */
    public int save(CourseTeacherDto courseTeacherDto) {

        List<Integer> tagIdList = Lists.newArrayList();

        //添加课程老师信息
        CourseTeacher courseTeacher = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacherDto);
        courseTeacher.setDeleteStatus(2);
        courseTeacherMapper.insertSelective(courseTeacher);

        //在课程老师标签关系表添加该老师与标签的对应关系
        //1.批量添加标签名称
        String[] tagNames = courseTeacherDto.getTagName();

        for (String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            Tag tagEntity = tagMapper.selectOne(tag);
            if (null == tagEntity){
                Tag newTag = new Tag();
                newTag.setDeleteStatus(2);
                newTag.setTagName(tagName);
                tagMapper.insert(newTag);
                tagIdList.add(newTag.getId());
            }else {
                tagIdList.add(tagEntity.getId());
            }
        }

        //2添加老师和标签的关系
        List<CourseTeacherTag> courseTeacherTagList = Lists.newArrayList();
        for (Integer tagId : tagIdList){
            CourseTeacherTag courseTeacherTag = new CourseTeacherTag();
            courseTeacherTag.setTagId(tagId);
            courseTeacherTag.setCourseTeacherId(courseTeacher.getId());
            courseTeacherTagList.add(courseTeacherTag);
        }
        return courseTeacherTagMapper.insertCourseTeacherTagByTags(courseTeacherTagList);
    }

    /**
     * 删除课程-教师
     *
     * @param id
     * @return
     */
    public int remove(Integer id) {
        return courseTeacherMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改课程-教师
     *
     * @param courseTeacher
     * @return
     */
    public int modify(CourseTeacher courseTeacher) {
        return courseTeacherMapper.updateByPrimaryKeySelective(courseTeacher);
    }

    /**
     * 通过老师id列表获取老师信息
     * @param teacherIds
     * @return
     */
    public List<CourseTeacher> getTeacherByTeacherIdList(List<Integer> teacherIds) {
        Example teacherExample = new Example(CourseTeacher.class);
        Example.Criteria cri = teacherExample.createCriteria();
        cri.andIn("id", teacherIds);
        return courseTeacherMapper.selectByExample(teacherExample);
    }

    /**
     * 根据课程ID插入授课老师
     * @param courseTeacher
     * @return
     */
    public int saveTeacherByCourseId(CourseTeacher courseTeacher) {

        return courseTeacherMapper.insert(courseTeacher);

    }
}
