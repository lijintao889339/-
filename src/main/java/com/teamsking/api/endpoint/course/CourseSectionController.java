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
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam(name = "courseSectionDto", value = "课程的节", required = true, dataType = "CourseSectionDto"),
            @ApiImplicitParam (name = "chapterId", value = "课程章的主键", required = true, dataType = "int")
    })
    @PostMapping("/course_section/{chapterId}")
    public Result addCourseSection(@RequestBody CourseSectionDto courseSectionDto,
                                   @PathVariable int chapterId){

        courseSectionService.save(courseSectionDto,chapterId);
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
     * @param courseSectionDto
     * @return
     */
    @ApiOperation(value = "修改课程的节", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseSection", value = "课程中的节", required = true, dataType = "CourseSectionDto"),
            @ApiImplicitParam (name = "id", value = "课程节的主键", required = true, dataType = "int")
    })
    @PutMapping("/course_section/{id}")
    public Result modify(@RequestBody CourseSectionDto courseSectionDto,
                         @PathVariable int id){

        courseSectionService.modify(courseSectionDto,id);
        return Result.success();
    }
}
