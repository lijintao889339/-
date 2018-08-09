package com.teamsking.domain.service.course;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.*;
import com.teamsking.domain.entity.course.CourseChapter;
import com.teamsking.domain.entity.course.CourseSection;
import com.teamsking.domain.repository.CourseChapterMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseChapterService {

    @Autowired
    CourseChapterMapper courseChapterMapper;

    @Autowired
    CourseSectionService courseSectionService;

    /**
     * 获取课程中章的列表
     * @return 章列表
     */
    public List<ChapterSectionDto> list(int courseId){

        List<Integer> chapterIds = Lists.newArrayList();

        //根据课程Id获取该课程下面的章
        CourseChapter courseChapterEntity = new CourseChapter();
        courseChapterEntity.setCourseId(courseId);
        courseChapterEntity.setDeleteStatus(2);
        List<CourseChapter> courseChapterList = courseChapterMapper.select(courseChapterEntity);

        //根据章Ids获取其下面的节
        for (CourseChapter courseChapter:courseChapterList) {
            chapterIds.add(courseChapter.getId());
        }
        List<CourseSection> courseSectionList = courseSectionService.getSectionListByChapterIds(chapterIds);

        //组装数据
        List<ChapterSectionDto> chapterSectionDtoList = CourseChapterDtoMapper.INSTANCE.entityListToChapterSectionDtoList(courseChapterList);

        //遍历集合
        for (ChapterSectionDto chapterSectionDto : chapterSectionDtoList){
            chapterSectionDto.setIsFirstLabel(true);

            List<SectionTitleAndOrderDto> sectionList = Lists.newArrayList();
            for (CourseSection courseSection : courseSectionList){
                SectionTitleAndOrderDto section = new SectionTitleAndOrderDto();
                if (chapterSectionDto.getId().intValue() == courseSection.getChapterId().intValue()){
                    section.setId(courseSection.getId());
                    section.setDiaplayOrder(courseSection.getDiaplayOrder());
                    section.setLabel(courseSection.getTitle());
                    section.setIsFirstLabel(false);
                    sectionList.add(section);
                    chapterSectionDto.setChildren(sectionList);
                }
            }
        }
        return chapterSectionDtoList;
    }

    /**
     * 添加课程中的章
     * @param courseChapterDto
     * @param courseId
     * @return
     */
    public int save(CourseChapterDto courseChapterDto, int courseId){

        //先查询此课程有多少章
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setCourseId(courseId);
        int count = courseChapterMapper.selectCount(courseChapter);

        CourseChapter newCourseChapter = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapterDto);
        newCourseChapter.setDeleteStatus(2);
        newCourseChapter.setDisplayOrder(count + 1);
        newCourseChapter.setDeleteStatus(1);
        return courseChapterMapper.insertSelective(newCourseChapter);
    }

    /**
     * 删除课程中的章
     * @param id
     * @return
     */
    public int remove(int id){

        //先将该章下的节删除
        courseSectionService.removeSectionByChapterId(id);

        //再删除该章
        CourseChapter courseChapter = new CourseChapter();
        courseChapter.setDeleteStatus(1);
        courseChapter.setId(id);
        return courseChapterMapper.updateByPrimaryKeySelective(courseChapter);
    }

    /**
     * 修改课程中的章
     * @param courseChapterDto
     * @return
     */
    public int modify(CourseChapterDto courseChapterDto){

        CourseChapter courseChapter = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapterDto);
        return courseChapterMapper.updateByPrimaryKeySelective(courseChapter);
    }

}
