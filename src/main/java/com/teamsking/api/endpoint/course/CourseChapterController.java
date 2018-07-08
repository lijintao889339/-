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
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "课程-章的列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_chapters")
    public Result CourseChapterList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<CourseChapter> courseChapterList = courseChapterService.list();
        List<CourseChapterDto> courseChapterDtoList = CourseChapterDtoMapper.INSTANCE.entityListToDtoList(courseChapterList);
        return Result.success().addData("pager",warpPage(courseChapterDtoList));
    }

    /**
     * 添加课程-章管理
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "添加课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @PostMapping("/course_chapter")
    public Result addCourseChapter(@RequestBody CourseChapterDto courseChapter){

        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        courseChapterService.save(courseChapterEntity);
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
     * @param id
     * @param courseChapter
     * @return
     */
    @ApiOperation(value = "修改课程中的章", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseChapter", value = "课程的章", required = true, dataType = "CourseChapterDto")
    })
    @PutMapping("/course_chapter/{id}")
    public Result modify(@PathVariable("id") int id,
                         @RequestBody CourseChapterDto courseChapter){

        CourseChapter courseChapterEntity = CourseChapterDtoMapper.INSTANCE.dtoToEntity(courseChapter);
        courseChapterEntity.setId(id);
        courseChapterService.modify(courseChapterEntity);
        return Result.success();
    }

}
