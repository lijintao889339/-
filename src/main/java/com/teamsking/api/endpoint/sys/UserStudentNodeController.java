package com.teamsking.api.endpoint.sys;

import com.github.pagehelper.Page;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.sys.UserStudentNodeService;
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

@Api(tags = "学生观看视频时长接口")
@Slf4j
@RestController
public class UserStudentNodeController extends BaseController {

    @Autowired
    UserStudentNodeService userStudentNodeService;

    @ApiOperation(value = "学生观看视频时长列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "nodeId", value = "视频的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int")
    })
    @GetMapping("/userStudentNodes/{openId}/{nodeId}")
    public Result userStudentNodeList(@RequestParam int pageNo, @RequestParam int pageSize,
                                      @PathVariable int nodeId, @PathVariable int openId) {

        Page page = userStudentNodeService.list(fixPage(pageNo),fixPage(pageSize),nodeId,openId);
        if (null == page){
            return Result.success().addData("pager", null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }


    }



    @ApiOperation(value = "根据班组搜索学生观看视频时长", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10"),
            @ApiImplicitParam(name = "nodeId", value = "视频的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "groupId", value = "班组的主键", required = true, dataType = "int")
    })
    @GetMapping("/searching_userStudentNodes/{openId}/{groupId}/{nodeId}")
    public Result userStudentNodeListByGroupId(@RequestParam int pageNo, @RequestParam int pageSize, @PathVariable int groupId,
                                      @PathVariable int nodeId, @PathVariable int openId) {

        Page page = userStudentNodeService.listByGroupId(fixPage(pageNo),fixPage(pageSize),nodeId,openId,groupId);
        if (null == page){
            return Result.success().addData("pager", null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }


    }

}
