package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseAssignmentDto;
import com.teamsking.api.dto.course.CourseAssignmentDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseAssignment;
import com.teamsking.domain.service.course.CourseAssignmentService;
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
@Api(tags = "课程-作业接口")
@Slf4j
public class CourseAssignmentController extends BaseController {

    @Autowired
    CourseAssignmentService courseAssignmentService;

    @ApiOperation(value = "课程-作业列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_assignments")
    public Result courseAssignmentList(int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));

        List<CourseAssignment> courseAssignmentList = courseAssignmentService.list();
        List<CourseAssignmentDto> courseAssignmentDtoList = CourseAssignmentDtoMapper.INSTANCE.entityListToDtoList(courseAssignmentList);
        return Result.success().addData("pager", warpPage(courseAssignmentDtoList));

    }

    @ApiOperation(value = "添加课程-作业", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseAssignment", value = "课程作业", required = true, dataType = "CourseAssignmentDto")
    })
    @PostMapping("/course_assignment")
    public Result addCourseAssignment(@RequestBody CourseAssignmentDto courseAssignment){

        CourseAssignment courseAssignmentEntity = CourseAssignmentDtoMapper.INSTANCE.dtoToEntity(courseAssignment);

        courseAssignmentService.save(courseAssignmentEntity);
        return Result.success();
    }

    @ApiOperation(value = "删除课程-作业", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "课程作业", required = true, dataType = "Integer")
    })
    @DeleteMapping("/course_assignment/{id}")
    public Result removeCourseAssignment(@PathVariable int id){

        courseAssignmentService.remove(id);
        return Result.success();

    }

    @ApiOperation(value = "修改课程-作业", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseAssignment", value = "课程作业", required = true, dataType = "CourseAssignmentDto")
    })
    @PutMapping("/course_assignment/{id}")
    public Result modifyCourseAssignment(@PathVariable int id,
                                         @RequestBody CourseAssignmentDto courseAssignment){

        CourseAssignment courseAssignmentEntity = CourseAssignmentDtoMapper.INSTANCE.dtoToEntity(courseAssignment);
        courseAssignmentEntity.setId(id);
        courseAssignmentService.modify(courseAssignmentEntity);
        return Result.success();
    }

}
