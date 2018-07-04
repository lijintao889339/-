package com.teamsking.api.endpoint.course;


import com.github.pagehelper.PageHelper;

import com.teamsking.api.dto.course.CourseTeacherDto;
import com.teamsking.api.dto.course.CourseTeacherDtoMapper;

import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.service.course.CourseTeacherService;
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
public class CourseTeacherController extends BaseController {

    @Autowired
    CourseTeacherService courseTeacherService;


    @ApiOperation(value = "课程-教师列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_teachers")
    public Result courseTeacherList(int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<CourseTeacher> courseTeacherList = courseTeacherService.list();
        List<CourseTeacherDto> courseTeacherDtoList = CourseTeacherDtoMapper.INSTANCE.entityListToDtoList(courseTeacherList);
        return Result.success().addData("pager", warpPage(courseTeacherDtoList));

    }

    @ApiOperation(value = "添加课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseTeacher", value = "课程教师", required = true, dataType = "CourseTeacherDto")
    })
    @PostMapping("/course_teacher")
    public Result addCourseTeacher(@RequestBody CourseTeacherDto courseTeacher){
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        courseTeacherService.save(courseTeacherEntity);
        return Result.success();
    }


    @ApiOperation(value = "删除课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "课程教师", required = true, dataType = "Integer")
    })
    @DeleteMapping("/course_teacher/{id}")
    public Result removeCourseTeacher(@PathVariable int id){
        courseTeacherService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改课程-教师", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseTeacher", value = "课程教师", required = true, dataType = "CourseTeacherDto")
    })
    @PutMapping("/course_teacher/{id}")
    public Result modfiyCourseTeacher(@PathVariable int id,
                                      @RequestBody CourseTeacherDto courseTeacher){
        CourseTeacher courseTeacherEntity = CourseTeacherDtoMapper.INSTANCE.dtoToEntity(courseTeacher);
        courseTeacherEntity.setId(id);
        courseTeacherService.modify(courseTeacherEntity);
        return Result.success();

    }

}
