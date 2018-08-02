package com.teamsking.domain.service.course;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.course.ChapterSectionDto;
import com.teamsking.api.dto.course.CourseChapterDtoMapper;
import com.teamsking.api.dto.course.CourseSectionDtoMapper;
import com.teamsking.api.dto.course.SectionTitleAndOrderDto;
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
                    section.setTitle(courseSection.getTitle());
                    section.setIsFirstLabel(false);
                    sectionList.add(section);
                    chapterSectionDto.setSectionDtoList(sectionList);
                }
            }
        }
        return chapterSectionDtoList;
    }

    /**
     * 添加课程中的章
     * @param courseChapter
     * @return
     */
    public int save(CourseChapter courseChapter){

        return courseChapterMapper.insert(courseChapter);
    }

    /**
     * 删除课程中的章
     * @param id
     * @return
     */
    public int remove(int id){

        return courseChapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程中的章
     * @param courseChapter
     * @return
     */
    public int modify(CourseChapter courseChapter){

        return courseChapterMapper.updateByPrimaryKeySelective(courseChapter);
    }

}
