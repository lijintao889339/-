package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.CourseSectionDto;
import com.teamsking.api.dto.CourseSectionDtoMapper;
import com.teamsking.domain.entity.CourseSection;
import com.teamsking.domain.service.CourseSectionService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@author linhao
*/
@RestController
@Api(tags = "课程中节的接口")
@Slf4j
public class CourseSectionController extends BaseController{

    @Autowired
    CourseSectionService courseSectionService;

    /**
     * 获取某一课程的某一章的所有节
     * @param courseId
     * @param chapterId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程中节的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_sections/course/{courseId}/chapter/{chapterId}")
    public Result courseSectionList(@PathVariable("courseId") int courseId,
                                    @PathVariable("chapterId") int chapterId,
                                    int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseSectionDto courseSection = new CourseSectionDto();
        courseSection.setCourseId(courseId);
        courseSection.setChapterId(chapterId);
        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSection);
        List<CourseSection> courseSectionList = courseSectionService.list(courseSectionEntity);

        List<CourseSectionDto> courseSectionDtoList = CourseSectionDtoMapper.INSTANCE.entityListToDtoList(courseSectionList);
        return Result.success().addData("pager",warpPage(courseSectionDtoList));
    }

    /**
     * 添加某一课程某一章的节
     * @param courseId
     * @param chapterId
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "添加课程中的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程的节", required = true, dataType = "CourseSectionDto")
    })
    @PostMapping("/course_section/course/{courseId}/chapter/{chapterId}")
    public Result addCourseSection(@PathVariable("courseId") int courseId,
                                   @PathVariable("chapterId") int chapterId,
                                   @RequestBody CourseSectionDto courseSectionDto){

        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        courseSectionEntity.setCourseId(courseId);
        courseSectionEntity.setChapterId(chapterId);
        courseSectionService.save(courseSectionEntity);
        return Result.success();
    }

    /**
     * 删除某一课程的某一章的指定节
     * @param id
     * @param courseId
     * @param chapterId
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "删除课程的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程中的节", required = true, dataType = "CourseSectionDto")
    })
    @DeleteMapping("/course_section/{id}/course/{courseId}/chapter/{chapterId}")
    public Result removeCourseSection(@PathVariable("id") int id,
                                      @PathVariable("courseId") int courseId,
                                      @PathVariable("chapterId") int chapterId,
                                      @RequestBody CourseSectionDto courseSectionDto){

        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        courseSectionEntity.setId(id);
        courseSectionEntity.setCourseId(courseId);
        courseSectionEntity.setChapterId(chapterId);
        courseSectionService.remove(courseSectionEntity);
        return Result.success();
    }

    /**
     * 修改某一课程的某一章的指定节
     * @param id
     * @param courseId
     * @param chapterId
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "删除课程的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程中的节", required = true, dataType = "CourseSectionDto")
    })
    @PutMapping("/course_section/{id}/course/{courseId}/chapter/{chapterId}")
    public Result modify(@PathVariable("id") int id,
                         @PathVariable("courseId") int courseId,
                         @PathVariable("chapterId") int chapterId,
                         @RequestBody CourseSectionDto courseSectionDto){

        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        courseSectionEntity.setId(id);
        courseSectionEntity.setCourseId(courseId);
        courseSectionEntity.setChapterId(chapterId);
        courseSectionService.remove(courseSectionEntity);
        courseSectionService.modify(courseSectionEntity);
        return Result.success();
    }
}
