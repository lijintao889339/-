package com.teamsking.api.endpoint;


import com.github.pagehelper.PageHelper;

import com.teamsking.api.dto.CourseTeacherDto;
import com.teamsking.api.dto.CourseTeacherDtoMapper;

import com.teamsking.domain.entity.CourseTeacher;
import com.teamsking.domain.service.CourseTeacherService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "课程-教师接口")
@Slf4j
public class CourseTeacherController extends BaseController{

    @Autowired
    CourseTeacherService courseTeacherService;


    @ApiOperation(value = "课程-教师列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/sys_user/{userId}/course_teachers/course/{courseId}")
    public Result courseTeacherList(@PathVariable int courseId,
                                    @PathVariable int userId,
                                    int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        CourseTeacherDto courseTeacher = new CourseTeacherDto();
        courseTeacher.setCourseId(courseId);
        courseTeacher.setUserId(userId);
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        List<CourseTeacher> courseTeacherList = courseTeacherService.list(courseTeacherEntity);
        List<CourseTeacherDto> courseTeacherDtoList = CourseTeacherDtoMapper.INSTANCE.entityListToDtoList(courseTeacherList);
        return Result.success().addData("pager", warpPage(courseTeacherDtoList));

    }

    @ApiOperation(value = "添加课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseTeacher", value = "课程教师", required = true, dataType = "CourseTeacherDto")
    })
    @PostMapping("/sys_user/{userId}/course_teacher/course/{courseId}")
    public Result addCourseTeacher(@PathVariable int courseId,
                                   @PathVariable int userId,
                                   @RequestBody CourseTeacherDto courseTeacher){
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        courseTeacherEntity.setCourseId(courseId);
        courseTeacherEntity.setUserId(userId);
        courseTeacherService.save(courseTeacherEntity);
        return Result.success();
    }


    @ApiOperation(value = "删除课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseTeacher", value = "课程教师", required = true, dataType = "CourseTeacherDto")
    })
    @DeleteMapping("/sys_user/{userId}/course_teacher/{id}/course/{courseId}")
    public Result removeCourseTeacher(@PathVariable int id,
                                      @PathVariable int courseId,
                                      @PathVariable int userId,
                                      @RequestBody CourseTeacherDto courseTeacher){
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        courseTeacherEntity.setId(id);
        courseTeacherEntity.setCourseId(courseId);
        courseTeacherEntity.setUserId(userId);
        courseTeacherService.remove(courseTeacherEntity);
        return Result.success();

    }

    @ApiOperation(value = "修改课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseTeacher", value = "课程教师", required = true, dataType = "CourseTeacherDto")
    })
    @PutMapping("/sys_user/{userId}/course_teacher/{id}/course/{courseId}")
    public Result modfiyCourseTeacher(@PathVariable int id,
                                      @PathVariable int courseId,
                                      @PathVariable int userId,
                                      @RequestBody CourseTeacherDto courseTeacher){
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        courseTeacherEntity.setId(id);
        courseTeacherEntity.setCourseId(courseId);
        courseTeacherEntity.setUserId(userId);
        courseTeacherService.modify(courseTeacherEntity);
        return Result.success();

    }

}
