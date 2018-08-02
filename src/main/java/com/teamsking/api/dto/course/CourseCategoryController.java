package com.teamsking.api.dto.course;

import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.course.CourseCategoryService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
*@author linhao
*/

@RestController
@Api(tags = "课程分类接口")
@Slf4j
public class CourseCategoryController extends BaseController {

    @Autowired
    CourseCategoryService courseCategoryService;

    @ApiOperation(value = "一级课程分类列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/course_categorys")
    public Result list(@RequestParam int pageNo, @RequestParam int pageSize){

        return Result.success().addData("pager",warpPage(courseCategoryService.list(fixPage(pageNo),fixPage(pageSize))));
    }

    /*@ApiOperation(value = "获取二级分类", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "一级课程分类主键", required = true, dataType = "int")
    })
    @GetMapping("/course_category/{id}")
    public Result list(@PathVariable int id){

        return Result.success().addData("pager",warpPage(courseCategoryService.list(fixPage(pageNo),fixPage(pageSize))));
    }
*/
}
