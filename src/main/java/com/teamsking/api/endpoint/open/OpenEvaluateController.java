package com.teamsking.api.endpoint.open;

import com.github.pagehelper.Page;
import com.teamsking.api.dto.open.OpenEvaluateDto;
import com.teamsking.api.dto.open.OpenEvaluateDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenEvaluate;
import com.teamsking.domain.service.open.OpenEvaluateService;
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
@Api(tags = "班课评价操作接口")
public class OpenEvaluateController extends BaseController {

    @Autowired
    OpenEvaluateService openEvaluateService;

    @ApiOperation(value = "班课评价", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_evaluates")
    public Result openEvaluateList(@RequestParam int pageNo,@RequestParam int pageSize){

        return Result.success().addData("pager", warpPage(openEvaluateService.list(fixPage(pageNo), fixPage(pageSize))));

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

    @ApiOperation(value = "批量删除班课评价", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "班课评价的主键", required = true)
    })
    @DeleteMapping("/open_evaluates/multi_delete")
    public Result removeMultiOpenEvaluates(@RequestParam Integer[] ids){

        openEvaluateService.removeOpenEvaluateByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "是否显示班课评价", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "班课评价的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "openEvaluate", value = "班课评价", required = true, dataType = "OpenEvaluateDto")
    })
    @PutMapping("/open_evaluate/{id}")
    public Result isShowOpenEvaluate(@PathVariable("id") int id,
                                       @RequestBody OpenEvaluateDto openEvaluate){

        OpenEvaluate openEvaluateEntity = OpenEvaluateDtoMapper.INSTANCE.dtoToEntity(openEvaluate);
        openEvaluateEntity.setId(id);
        openEvaluateService.isShowById(openEvaluateEntity);
        return Result.success();
    }



    @ApiOperation(value = "通过搜索条件获取班课评价", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", paramType = "query", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "categoryId", paramType = "query", value = "课程分类模板主键", dataType = "Integer"),
            @ApiImplicitParam(name = "teacherId", paramType = "query", value = "授课老师主键", dataType = "Integer")
    })
    @GetMapping("/searching_open_evaluates")
    public Result getCourseEvaluateByCourse(@RequestParam int pageNo, @RequestParam int pageSize,
                                            @RequestParam Integer categoryId, @RequestParam Integer teacherId){

        Page page = openEvaluateService.listBySearching(fixPage(pageNo), fixPage(pageSize),categoryId,teacherId);

        if (null == page){
            return Result.exception("查询无果");
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }
}
