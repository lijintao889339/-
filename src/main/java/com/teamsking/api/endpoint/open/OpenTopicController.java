package com.teamsking.api.endpoint.open;

import com.github.pagehelper.Page;
import com.teamsking.api.dto.open.OpenTopicDto;
import com.teamsking.api.dto.open.OpenTopicNameDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenTopic;
import com.teamsking.domain.service.open.OpenTopicService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "班课讨论操作接口")
public class OpenTopicController extends BaseController {

    @Autowired
    OpenTopicService openTopicService;


    @ApiOperation(value = "根据班课id添加讨论", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openTopicDto", value = "班次讨论", required = true, dataType = "OpenTopicDto")
    })
    @PostMapping("/open_topic")
    public Result saveOpenTopic(@RequestBody OpenTopicDto openTopicDto){

        openTopicService.saveOpenTopic(openTopicDto);
        return Result.success();

    }



    @ApiOperation(value = "根据节id添加讨论", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openTopicDto", value = "班次讨论", required = true, dataType = "OpenTopicDto"),
            @ApiImplicitParam(name = "sectionId", value = "节id", required = true, dataType = "Integer")
    })
    @PostMapping("/open_topic/{sectionId}")
    public Result saveOpenTopicBySectionId(@RequestBody OpenTopicDto openTopicDto,
                                           @PathVariable Integer sectionId){

        openTopicService.saveOpenTopicBySectionId(openTopicDto,sectionId);
        return Result.success();

    }



    @ApiOperation(value = "根据班课id获取讨论列表", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "openId", value = "班课的id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNo", paramType = "query",value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", paramType = "query", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_topic/{openId}")
    public Result getOpenTopicByOpenIdList(@PathVariable int openId, @RequestParam int pageNo, @RequestParam int pageSize){

        Page page = openTopicService.getOpenOpenTopicListByOpenId(openId,fixPage(pageNo),fixPage(pageSize));
        if (null == page){
            return Result.success().addData("pager",null);
        }else {
            return Result.success().addData("pager", warpPage(page));
        }
    }


    @ApiOperation(value = "班课讨论详情",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讨论主键", required = true, dataType = "int")
    })
    @GetMapping("/open_topic_detail/{id}")
    public Result openTopic(@PathVariable int id){

        OpenTopic openTopic = openTopicService.getOpenTopicById(id);

        return Result.success().addData("openTopic", openTopic);

    }

}
