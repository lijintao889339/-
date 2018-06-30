package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseChapterDto;
import com.teamsking.api.dto.course.CourseChapterDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseChapter;
import com.teamsking.domain.service.course.CourseChapterService;
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
@Api(tags = "课程中章的接口")
@Slf4j
public class CourseChapterController extends BaseController {

    @Autowired
    CourseChapterService courseChapterService;

    /**
     * 获取某一课程下面的章列表
     * @param courseId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程章的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course/{courseId}/course_chapters")
    public Result CourseChapterList(@PathVariable("courseId") int courseId,
                                    int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        CourseChapterDto courseChapter = new CourseChapterDto();
        courseChapter.setCourseId(courseId);
        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        List<CourseChapter> courseChapterList = courseChapterService.list(courseChapterEntity);

        List<CourseChapterDto> courseChapterDtoList = CourseChapterDtoMapper.INSTANCE.entityListToDtoList(courseChapterList);
        return Result.success().addData("pager",warpPage(courseChapterDtoList));
    }

    /**
     * 添加某一课程下面的章
     * @param courseId
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "添加课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @PostMapping("/course/{courseId}/course_chapter")
    public Result addCourseChapter(@PathVariable("courseId") int courseId,
                                   @RequestBody CourseChapterDto courseChapter){

        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        courseChapterEntity.setCourseId(courseId);
        courseChapterService.save(courseChapterEntity);
        return Result.success();
    }

    /**
     * 删除某一课程下面的指定章
     * @param id
     * @param courseId
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "删除课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @DeleteMapping("/course/{courseId}/course_chapter/{id}")
    public Result removeCourseChapter(@PathVariable("id") int id,
                                      @PathVariable("courseId") int courseId,
                                      @RequestBody CourseChapterDto courseChapter){

        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        courseChapterEntity.setId(id);
        courseChapterEntity.setCourseId(courseId);
        courseChapterService.remove(courseChapterEntity);
        return Result.success();
    }

    /**
     * 修改某一课程的指定章
     * @param id
     * @param courseId
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "修改课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @PutMapping("/course/{courseId}/course_chapter/{id}")
    public Result modify(@PathVariable("id") int id,
                         @PathVariable("courseId") int courseId,
                         @RequestBody CourseChapterDto courseChapter){

        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        courseChapterEntity.setId(id);
        courseChapterEntity.setCourseId(courseId);
        courseChapterService.modify(courseChapterEntity);
        return Result.success();
    }

}
