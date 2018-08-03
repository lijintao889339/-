package com.teamsking.domain.service.course;

import com.teamsking.api.dto.course.CourseSectionDto;
import com.teamsking.api.dto.course.CourseSectionDtoMapper;
import com.teamsking.domain.entity.course.CourseSection;
import com.teamsking.domain.repository.CourseChapterMapper;
import com.teamsking.domain.repository.CourseSectionMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseSectionService {

    @Autowired
    CourseSectionMapper courseSectionMapper;
    @Autowired
    CourseChapterMapper courseChapterMapper;

    /**
     * 获取课程中节的列表
     * @return
     */
    public List<CourseSection> list(){

        return courseSectionMapper.selectAll();
    }

    /**
     * 根据章Ids获取其下面的节
     * @param chapterIds
     * @return
     */
    public List<CourseSection> getSectionListByChapterIds(List<Integer> chapterIds){

        Example sectionExample = new Example(CourseSection.class);
        Example.Criteria cri = sectionExample.createCriteria();
        cri.andIn("chapterId",chapterIds);
        cri.andEqualTo("deleteStatus",2);
        return courseSectionMapper.selectByExample(sectionExample);
    }

    /**
     * 添加课程中的节
     * @param courseSectionDto
     * @return
     */
    public int save(CourseSectionDto courseSectionDto){

        //先查询此章有多少节
        CourseSection courseSection = new CourseSection();
        courseSection.setChapterId(courseSectionDto.getChapterId());
        int count = courseSectionMapper.selectCount(courseSection);

        CourseSection newCourseSection = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        newCourseSection.setDeleteStatus(2);
        newCourseSection.setDiaplayOrder(count + 1);
        return courseSectionMapper.insertSelective(newCourseSection);
    }

    /**
     * 删除课程中的节
     * @param id
     * @return
     */
    public int remove(int id){

        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setDeleteStatus(1);
        return courseSectionMapper.updateByPrimaryKeySelective(courseSection);
    }

    /**
     * 修改课程中的节
     * @param courseSectionDto
     * @return
     */
    public int modify(CourseSectionDto courseSectionDto){

        CourseSection courseSection = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        return courseSectionMapper.updateByPrimaryKeySelective(courseSection);
    }

    /**
     * 根据章Id删除此章下的节
     * @param chapterId
     * @return
     */
    public int removeSectionByChapterId(int chapterId) {

        CourseSection courseSection = new CourseSection();
        courseSection.setDeleteStatus(1);

        Example courseSectinExample = new Example(CourseSection.class);
        courseSectinExample.or().andEqualTo("chapterId",chapterId);
        return courseSectionMapper.updateByExampleSelective(courseSection,courseSectinExample);
    }
}
