package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseSectionDto;
import com.teamsking.api.dto.course.CourseSectionDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseSection;
import com.teamsking.domain.service.course.CourseSectionService;
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
public class CourseSectionController extends BaseController {

    @Autowired
    CourseSectionService courseSectionService;

    /**
     * 获取课程-节
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程中节的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_sections")
    public Result courseSectionList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<CourseSection> courseSectionList = courseSectionService.list();

        List<CourseSectionDto> courseSectionDtoList = CourseSectionDtoMapper.INSTANCE.entityListToDtoList(courseSectionList);
        return Result.success().addData("pager",warpPage(courseSectionDtoList));
    }

    /**
     * 添加课程-节
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "添加课程中的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程的节", required = true, dataType = "CourseSectionDto")
    })
    @PostMapping("/course_section")
    public Result addCourseSection(@RequestBody CourseSectionDto courseSectionDto){

        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        courseSectionService.save(courseSectionEntity);
        return Result.success();
    }

    /**
     * 删除课程-节
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程中的节的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_section/{id}")
    public Result removeCourseSection(@PathVariable("id") int id){

        courseSectionService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-节
     * @param id
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "删除课程的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程中的节", required = true, dataType = "CourseSectionDto")
    })
    @PutMapping("/course_section/{id}")
    public Result modify(@PathVariable("id") int id,
                         @RequestBody CourseSectionDto courseSectionDto){

        CourseSection courseSectionEntity = CourseSectionDtoMapper.INSTANCE.dtoToEntity(courseSectionDto);
        courseSectionEntity.setId(id);
        courseSectionService.modify(courseSectionEntity);
        return Result.success();
    }
}
