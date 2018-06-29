package com.teamsking.api.endpoint.course;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.course.CourseWareDto;
import com.teamsking.api.dto.course.CourseWareDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseWare;
import com.teamsking.domain.repository.CourseWareMapper;
import com.teamsking.domain.service.course.CourseWareService;
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
@Slf4j
@Api(tags = "课程-课件管理操作接口")
public class CourseWareController extends BaseController {

    @Autowired
    CourseWareService courseWareService;


    @ApiOperation(value = "课程-课件列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course/{courseId}/course_wares")
    public Result courseWareList(@PathVariable int courseId,
                                 int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        CourseWareDto courseWare = new CourseWareDto();
        courseWare.setCourseId(courseId);
        CourseWare courseWareEntity = CourseWareDtoMapper.INSTANCE.dtoToEntity(courseWare);
        List<CourseWare> courseWareList = courseWareService.list(courseWareEntity);
        List<CourseWareDto> courseWareDtoList = CourseWareDtoMapper.INSTANCE.entityListToDtoList(courseWareList);
        return Result.success().addData("pager",warpPage(courseWareDtoList));

    }


    @ApiOperation(value = "添加课程-课件", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseWare", value = "课程课件", required = true, dataType = "CourseWareDto")
    })
    @PostMapping("/course/{courseId}/course_ware")
    public Result addCourseWare(@PathVariable int courseId,
                                @RequestBody CourseWareDto courseWare){

        CourseWare courseWareEntity = CourseWareDtoMapper.INSTANCE.dtoToEntity(courseWare);
        courseWareEntity.setCourseId(courseId);
        courseWareService.save(courseWareEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除课程-课件", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseWare", value = "课程课件", required = true, dataType = "CourseWareDto")
    })
    @DeleteMapping("/course/{courseId}/course_ware/{id}")
    public Result removeCourseWare(@PathVariable int courseId,
                                   @PathVariable int id,
                                   @RequestBody CourseWareDto courseWare){
        CourseWare courseWareEntity = CourseWareDtoMapper.INSTANCE.dtoToEntity(courseWare);
        courseWareEntity.setCourseId(courseId);
        courseWareEntity.setId(id);
        courseWareService.remove(courseWareEntity);
        return Result.success();

    }


    @ApiOperation(value = "修改课程-课件", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "courseWare", value = "课程课件", required = true, dataType = "CourseWareDto")
    })
    @PutMapping("/course/{courseId}/course_ware/{id}")
    public Result modifyCourseWare(@PathVariable int courseId,
                                   @PathVariable int id,
                                   @RequestBody CourseWareDto courseWare){

        CourseWare courseWareEntity = CourseWareDtoMapper.INSTANCE.dtoToEntity(courseWare);
        courseWareEntity.setId(id);
        courseWareEntity.setCourseId(courseId);
        courseWareService.modify(courseWareEntity);

        return Result.success();

    }

}
