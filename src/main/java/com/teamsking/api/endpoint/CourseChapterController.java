package com.teamsking.api.endpoint;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.CourseChapterDto;
import com.teamsking.api.dto.CourseChapterDtoMapper;
import com.teamsking.domain.entity.CourseChapter;
import com.teamsking.domain.service.CourseChapterService;
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
     * 获取某一课程的所有章的列表
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
    @GetMapping("/course_chapters/course/{courseId}")
    public Result CourseChapterList(@PathVariable("courseId") int courseId,
                                    int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        CourseChapterDto courseChapterDto = new CourseChapterDto();
        courseChapterDto.setCourseId(courseId);
        CourseChapter entity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapterDto);
        List<CourseChapter> courseChapterList = courseChapterService.list(entity);

        List<CourseChapterDto> courseChapterDtoList = CourseChapterDtoMapper.INSTANCE.entityListToDtoList(courseChapterList);
        return Result.success().addData("pager",warpPage(courseChapterDtoList));
    }

    /**
     * 添加某一课程的章
     * @param courseId
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "添加课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @PostMapping("/course_chapter/course/{courseId}")
    public Result addCourseChapter(@PathVariable("courseId") int courseId,
                                   @RequestBody CourseChapterDto courseChapter){

        CourseChapter entity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        entity.setCourseId(courseId);
        courseChapterService.save(entity);
        return Result.success();
    }

    /**
     * 删除某一课程的指定章
     * @param id
     * @param courseId
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "删除课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @DeleteMapping("/course_chapter/{id}/course/{courseId}")
    public Result removeCourseChapter(@PathVariable("id") int id,
                                      @PathVariable("courseId") int courseId,
                                      @RequestBody CourseChapterDto courseChapter){

        CourseChapter entity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        entity.setId(id);
        entity.setCourseId(courseId);
        courseChapterService.remove(entity);
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
    @PutMapping("/course_chapter/{id}/course/{courseId}")
    public Result modify(@PathVariable("id") int id,
                         @PathVariable("courseId") int courseId,
                         @RequestBody CourseChapterDto courseChapter){

        CourseChapter entity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        entity.setId(id);
        entity.setCourseId(courseId);
        courseChapterService.modify(entity);
        return Result.success();
    }

}
