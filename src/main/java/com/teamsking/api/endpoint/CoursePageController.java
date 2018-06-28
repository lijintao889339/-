package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.CoursePageDto;
import com.teamsking.api.dto.CoursePageDtoMapper;
import com.teamsking.domain.entity.CoursePage;
import com.teamsking.domain.service.CoursePageService;
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
@Slf4j
@Api(tags = "课程-章节页面管理接口")
public class CoursePageController extends BaseController{

    @Autowired
    CoursePageService coursePageService;

    /**
     * 获取某一课程的所有章节页面管理的列表
     * @param courseId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-章节页面列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("courses/{courseId}/course_pages")
    public Result coursePageList(@PathVariable("courseId") int courseId,
                                 int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CoursePageDto coursePage = new CoursePageDto();
        coursePage.setCourseId(courseId);
        CoursePage coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        List<CoursePage> coursePageList = coursePageService.list(coursePageEntity);

        List<CoursePageDto> coursePageDtoList = CoursePageDtoMapper.INSTANCE.entityListToDtoList(coursePageList);
        return Result.success().addData("pager",warpPage(coursePageDtoList));
    }

    /**
     * 添加某一课程中的章节页面
     * @param courseId
     * @param coursePage
     * @return
     */
    @ApiOperation(value = "添加课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePage", value = "课程-章节页面", required = true, dataType = "CoursePageDto")
    })
    @PostMapping("course/{courseId}/course_page")
    public Result addCoursePage(@PathVariable("courseId") int courseId,
                                @RequestBody CoursePageDto coursePage){

        CoursePage  coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        coursePageEntity.setCourseId(courseId);
        coursePageService.save(coursePageEntity);
        return Result.success();
    }

    /**
     * 删除某一课程中的指定章节页面
     * @param id
     * @param courseId
     * @param coursePage
     * @return
     */
    @ApiOperation(value = "删除课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePage", value = "课程-章节页面", required = true, dataType = "CoursePageDto")
    })
    @DeleteMapping("/course/{courseId}/course_page/{id}")
    public Result removeCoursePage(@PathVariable("id") int id,
                                   @PathVariable("courseId") int courseId,
                                   @RequestBody CoursePageDto coursePage){

        CoursePage  coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        coursePageEntity.setId(id);
        coursePageEntity.setCourseId(courseId);
        coursePageService.remove(coursePageEntity);
        return Result.success();
    }

    /**
     * 修改某一课程中的指定章节页面
     * @param id
     * @param courseId
     * @param coursePage
     * @return
     */
    @ApiOperation(value = "修改课程-章节页面", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePage", value = "课程-章节页面", required = true, dataType = "CoursePageDto")
    })
    @PutMapping("course/{courseId}/course_page/{id}")
    public Result modifyCoursePage(@PathVariable("id") int id,
                                   @PathVariable("courseId") int courseId,
                                   @RequestBody CoursePageDto coursePage){

        CoursePage  coursePageEntity = CoursePageDtoMapper.INSTANCE.dtoToEntity(coursePage);
        coursePageEntity.setId(id);
        coursePageEntity.setCourseId(courseId);
        coursePageService.modify(coursePageEntity);
        return Result.success();
    }

}
