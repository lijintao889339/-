package com.teamsking.api.endpoint.open;

import com.github.pagehelper.Page;
import com.teamsking.api.dto.open.OpenExamDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.open.OpenExamService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Api(tags = "班课考试接口")
public class OpenExamController extends BaseController {


    @Autowired
    OpenExamService openExamService;


    @ApiOperation(value = "添加班课—考试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openExamDto", value = "班课考试", required = true, dataType = "OpenExamDto")
    })
    @PostMapping("/open_exam")
    public Result saveOpenExam(@RequestBody OpenExamDto openExamDto){

        openExamService.saveOpenExam(openExamDto);

        return Result.success();

    }


    @ApiOperation(value = "根据班课id获取考试列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课的id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_exam/{openId}")
    public Result getOpenUser(@PathVariable int openId, @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = openExamService.openExamList(openId,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }




    @ApiOperation(value = "模糊查询考试列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课的id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "title", value = "考试标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String")
    })
    @GetMapping("/open_exam_name/{openId}")
    public Result getOpenUserList(@PathVariable int openId, @RequestParam int pageNo, @RequestParam int pageSize,
                                  @RequestParam String title,
                                  @RequestParam String type){

        Page page = openExamService.openExamListByName(openId,fixPage(pageNo),fixPage(pageSize),title,type);
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }


}
