package com.teamsking.api.endpoint.open;

import com.teamsking.api.dto.open.OpenTopicDto;
import com.teamsking.api.dto.open.OpenTopicNameDto;
import com.teamsking.api.endpoint.BaseController;
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




    @ApiOperation(value = "获取班课讨论列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "班课的主键", required = true, dataType = "int"),
    })
    @GetMapping("/open_topic/{openId}")
    public Result announceList(@PathVariable int openId){

        List<OpenTopicNameDto> openTopicNameDtoList = openTopicService.getOpenOpenTopicListByOpenId(openId);
        return Result.success().addData("openTopicNameDtoList",openTopicNameDtoList);
    }

}
