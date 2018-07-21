package com.teamsking.api.endpoint.course;

import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.course.CourseEvaluateService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

}
