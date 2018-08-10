package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.ChapterSectionDto;
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
@Api(tags = "课程中章的接口")
@Slf4j
public class CourseChapterController extends BaseController {

    @Autowired
    CourseChapterService courseChapterService;

    /**
     * 获取课程-章列表管理
     * @param courseId
     * @return
     */
    @ApiOperation(value = "课程-章的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程的主键", required = true, dataType = "int")
    })
    @GetMapping("/course_chapters/{courseId}")
    public Result CourseChapterList(@PathVariable int courseId){

        List<ChapterSectionDto> chapterSectionDtoList = courseChapterService.list(courseId);
        if (null == chapterSectionDtoList){
            return Result.success().addData("chapterSectionDtoList","");
        }else {
            return Result.success().addData("chapterSectionDtoList",chapterSectionDtoList);
        }

    }

    /**
     * 添加课程-章管理
     * @param courseChapterDto
     * @return
     */
    @ApiOperation(value = "添加某一课程的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "courseChapterDto", value = "课程的章", required = true, dataType = "CourseChapterDto"),
            @ApiImplicitParam (name = "courseId", value = "课程的主键", required = true, dataType = "int")
    })
    @PostMapping("/course_chapter/{courseId}")
    public Result addCourseChapter(@PathVariable int courseId,
                                   @RequestBody CourseChapterDto courseChapterDto){

        courseChapterService.save(courseChapterDto,courseId);
        return Result.success();
    }

    /**
     * 删除课程-章管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程-章的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/course_chapter/{id}")
    public Result removeCourseChapter(@PathVariable("id") int id){

        courseChapterService.remove(id);
        return Result.success();
    }

    /**
     * 修改课程-章管理
     * @param courseChapterDto
     * @return
     */
    @ApiOperation(value = "修改课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseChapterDto", value = "课程的章", required = true, dataType = "CourseChapterDto"),
            @ApiImplicitParam (name = "courseId", value = "课程的主键", required = true, dataType = "int")
    })
    @PutMapping("/course_chapter/{id}")
    public Result modify(@RequestBody CourseChapterDto courseChapterDto,
                         @PathVariable int id){

        courseChapterService.modify(courseChapterDto,id);
        return Result.success();
    }

}
