package com.teamsking.api.endpoint.course;

import com.teamsking.api.dto.course.CourseEvaluateDto;
import com.teamsking.api.dto.course.CourseEvaluateDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.course.CourseEvaluate;
import com.teamsking.domain.repository.CourseEvaluateMapper;
import com.teamsking.domain.service.course.CourseEvaluateService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@Api(tags = "课程评价操作接口")
public class CourseEvaluateController extends BaseController {

    @Autowired
    CourseEvaluateService courseEvaluateService;

    @ApiOperation(value = "课程评价", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_evaluate")
    public Result courseEvaluateList(@RequestParam int pageNo,
                                     @RequestParam int pageSize){

        return Result.success().addData("pager", warpPage(courseEvaluateService.list(fixPage(pageNo), fixPage(pageSize))));

    }

//    @ApiOperation(value ="课程评价详情", produces = "application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",paramType = "query", value = "课程评价的主键", required = true, dataType = "int")
//    })
//    @GetMapping("/course_evaluate/{id}/information")
//    public Result getCourseEvaluteInfo(@PathVariable("id") int id){
//
//        return Result.success().addData("pager",courseEvaluateService.getCourseEvaluteInfoById(id));
//
//    }

    @ApiOperation(value = "批量删除课程评价", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "课程评价的主键", required = true)
    })
    @DeleteMapping("/course_evaluates/multi_delete")
    public Result removeMultiCourseEvaluates(@RequestParam Integer[] ids){

        courseEvaluateService.removeCourseEvaluateByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "是否显示课程评价", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "课程评价的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "courseEvaluate", value = "课程评价", required = true, dataType = "CourseEvaluateDto")
    })
    @PutMapping("/course_evaluate/{id}")
    public Result isShowCourseEvaluate(@PathVariable("id") int id,
                                             @RequestBody CourseEvaluateDto courseEvaluate){

        CourseEvaluate courseEvaluateEntity = CourseEvaluateDtoMapper.INSTANCE.dtoToEntity(courseEvaluate);
        courseEvaluateEntity.setId(id);
        courseEvaluateService.isShowById(courseEvaluateEntity);
        return Result.success();
    }

}
